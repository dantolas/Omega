package com.kuta.app.controllers;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuta.app.services.LoginService;
import com.kuta.app.services.SignupService;
import com.kuta.app.services.SignupService.STATUS_CODE;
import com.kuta.db.ConnectorInitException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * AuthController
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    private record LoginData(String login,String password){}
    private record OkLoginResponse(String status){}
    private record BadLoginResponse(String status,String error){}

    

    @GetMapping(
        value = "authenticated",
        produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<?> isAuthenticated(HttpServletRequest request) throws ConnectorInitException, SQLException{
        logger.info("LOGIN SESSION:"+request.getSession().getId());
        Object user = request.getSession().getAttribute("user");
        if(user != null){
            logger.info("User is authenticated:"+user);
            return ResponseEntity.ok(new OkLoginResponse("true"));
        }
        return ResponseEntity.ok(new OkLoginResponse("false"));
    };
    @PostMapping(
        value = "login",
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
        },
        produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<?> login(@RequestBody LoginData login,HttpServletRequest request) throws ConnectorInitException, SQLException{
        logger.info("LOGIN SESSION:"+request.getSession().getId());
        if(!LoginService.validateLogin(login.login(),login.password())){
            logger.info("Login failed: invalid credentials");
           return  ResponseEntity.ok(new BadLoginResponse("failed","invalid_credentials."));
        }
        request.getSession().setAttribute("user", login.login()+LocalDateTime.now());
            logger.info("New log in:"+login.login());
        return ResponseEntity.ok(new OkLoginResponse("ok"));
    };

    private record SignupData(String username, String email, String password){}
    private record OkSignupResponse(String status){}
    private record BadSignupResponse(String status,String error){}
    @PostMapping(
        value = "signup", 
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> signup(@RequestBody SignupData signup,HttpServletRequest request) throws ConnectorInitException, SQLException{
        STATUS_CODE status = SignupService.signup(signup.username, signup.email, signup.password);
        if(status == STATUS_CODE.EMAIL_TAKEN){
            return ResponseEntity.ok(new BadSignupResponse("failed", "email_taken"));
        }
        if(status== STATUS_CODE.SQL_ERROR){
            return ResponseEntity.ok(new BadSignupResponse("failed", "sql_error"));
        }
        return ResponseEntity.ok(new OkSignupResponse("ok")); 
    }

    private record OkLogoutResponse(String status){}
    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<?> handleException(){
        return ResponseEntity.ok(new OkLogoutResponse("ok"));
    }
    @DeleteMapping("logout")
    public ResponseEntity<?> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok(new OkLogoutResponse("ok"));
    }
    
}
