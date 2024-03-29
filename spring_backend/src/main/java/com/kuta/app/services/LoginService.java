package com.kuta.app.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kuta.db.ConnectorInitException;
import com.kuta.db.DBAPI;
import com.kuta.db.UserModel;
import com.kuta.util.EmailValidator;

/**
 * LoginService
 */
@Service
public class LoginService {

    public static PasswordEncoder encoder;
    @Autowired
    public LoginService(PasswordEncoder encoder){
        LoginService.encoder = encoder;
    }

    public static boolean validateLogin(String usernameOrEmail,String password) throws ConnectorInitException, SQLException{

        if(EmailValidator.isEmail(usernameOrEmail)) {
            return authEmail(usernameOrEmail,password);
        }

        return authUsername(usernameOrEmail,password);
    }

    private static boolean authEmail(String email, String password) throws ConnectorInitException, SQLException{
        UserModel user = DBAPI.getUserByEmail(email);
        if(user == null) return false;
        if(!encoder.matches(password+user.salt(),user.passHash())) return false;
        return true;
    }

    private static boolean authUsername(String username, String password) throws ConnectorInitException, SQLException{
        UserModel user = DBAPI.getUserByUsername(username);
        if(user == null) return false;
        if(!encoder.matches(password+user.salt(),user.passHash())) return false;
        return true;
    }
    
}
