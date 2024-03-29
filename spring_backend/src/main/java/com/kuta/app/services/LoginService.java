package com.kuta.app.services;

import org.springframework.stereotype.Service;

import com.kuta.util.EmailValidator;

/**
 * LoginService
 */
@Service
public class LoginService {

    public static boolean validateLogin(String usernameOrEmail,String password){

        if(EmailValidator.isEmail(usernameOrEmail)) {
            return authEmail(usernameOrEmail,password);
        }

        return authUsername(usernameOrEmail,password);
    }

    private static boolean authEmail(String email, String password){
        return false;
    }

    private static boolean authUsername(String username, String password){
        return false;
    }
    
}
