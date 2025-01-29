package com.auth.server.exception;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> handlePSQLException(PSQLException ex) {
        if (ex.getMessage().contains("duplicate key value violates unique constraint")) {
            return new ResponseEntity<>("This email address is already in use.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("A database error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
