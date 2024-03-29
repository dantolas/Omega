package com.kuta.app.excHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kuta.db.ConnectorInitException;

/**
 * ControllerExceptionHandler
 */
@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(ConnectorInitException.class)
    public ResponseEntity<?> handle(ConnectorInitException e){
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    
}
