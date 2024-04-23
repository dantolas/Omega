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
 * LoginService handles user authentication.
 */
@Service
public class LoginService {

    public static PasswordEncoder encoder;

    /**
     * Constructor for LoginService.
     * @param encoder PasswordEncoder object
     */
    @Autowired
    public LoginService(PasswordEncoder encoder) {
        LoginService.encoder = encoder;
    }

    /**
     * Validates user login.
     * @param usernameOrEmail Username or email of the user
     * @param password Password of the user
     * @return true if login is successful, false otherwise
     * @throws ConnectorInitException if connector initialization fails
     * @throws SQLException if SQL operation fails
     */
    public static boolean validateLogin(String usernameOrEmail, String password) throws ConnectorInitException, SQLException {

        if (EmailValidator.isEmail(usernameOrEmail)) {
            return authEmail(usernameOrEmail, password);
        }

        return authUsername(usernameOrEmail, password);
    }

    private static boolean authEmail(String email, String password) throws ConnectorInitException, SQLException {
        UserModel user = DBAPI.getUserByEmail(email);
        if (user == null)
            return false;
        if (!encoder.matches(password + user.salt(), user.passHash()))
            return false;
        return true;
    }

    private static boolean authUsername(String username, String password) throws ConnectorInitException, SQLException {
        UserModel user = DBAPI.getUserByUsername(username);
        if (user == null)
            return false;
        if (!encoder.matches(password + user.salt(), user.passHash()))
            return false;
        return true;
    }

}
