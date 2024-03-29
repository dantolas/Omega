package com.kuta.app.services;

import java.security.SecureRandom;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kuta.db.ConnectorInitException;
import com.kuta.db.DBAPI;
import com.kuta.db.UserModel;

/**
 * SignupService
 */
@Service
public class SignupService {

    public static enum STATUS_CODE {
        SUCCESS,
        EMAIL_TAKEN,
        SQL_ERROR
    }

    public static PasswordEncoder encoder;
    @Autowired
    public SignupService(PasswordEncoder encoder){
        SignupService.encoder = encoder;
    }

    public static STATUS_CODE signup(String username, String email, String password) throws ConnectorInitException, SQLException {
        if(!emailAvailable(email)) {
            return STATUS_CODE.EMAIL_TAKEN;
        }
        SecureRandom random = new SecureRandom();
        byte[] saltbytes= new byte[16];
        random.nextBytes(saltbytes);
        String salt = new String(saltbytes);
        
        String passHash = encoder.encode(password+salt);
        UserModel user = new UserModel(username, email, passHash, salt);
        if(!DBAPI.insertUser(user)){
            return  STATUS_CODE.SQL_ERROR;
        };

        return STATUS_CODE.SUCCESS; 
    }

    private static boolean emailAvailable(String email) throws ConnectorInitException, SQLException{
        return DBAPI.getUserByEmail(email) == null;
    }
    
}
