package com.kuta.app.excHandlers;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ch.qos.logback.classic.Logger;

/**
 * ControllerExceptionHandler
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handle(NullPointerException e){
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request was sent."
            +"Make sure your request is in the proper format");
    }
    
}
