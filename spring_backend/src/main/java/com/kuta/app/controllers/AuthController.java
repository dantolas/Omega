package com.kuta.app.controllers;

import java.sql.SQLException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuta.app.services.LoginService;
import com.kuta.app.services.SignupService;
import com.kuta.app.services.SignupService.STATUS_CODE;
import com.kuta.db.ConnectorInitException;

/**
 * AuthController
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private record LoginData(String login,String password){}
    private record LoginResponse(boolean success,String sessionId,String failReason){}
    @PostMapping(
        value = "login",
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
        },
        produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
    )
    public LoginResponse login(@RequestBody LoginData login) throws ConnectorInitException, SQLException{
        if(!LoginService.validateLogin(login.login(),login.password())){
            return new LoginResponse(false,null,"Invalid login credentials.");
        }
        return new LoginResponse(true,"",null);
    };

    private record SignupData(String username, String email, String password){}

    private record SignupResponse(boolean success, String failReason){}
    @PostMapping(
        value = "signup", 
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SignupResponse signup(@RequestBody SignupData signup) throws ConnectorInitException, SQLException{
        STATUS_CODE status = SignupService.signup(signup.username, signup.email, signup.password);
        if(status == STATUS_CODE.EMAIL_TAKEN){
            return new SignupResponse(false, "email_taken");
        }
        if(status== STATUS_CODE.SQL_ERROR){
            return new SignupResponse(false, "sql_error");
        }
        return new SignupResponse(true,null); 
    }
}
