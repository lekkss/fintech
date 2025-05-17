package com.lekkss.fintech.ReqRes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        return ResponseHandler.generateResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                null,
                true);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseHandler.generateResponse(
                "Access denied: You don't have permission to access this resource",
                HttpStatus.FORBIDDEN,
                null,
                true);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        return ResponseHandler.generateResponse(
                "Authentication failed: " + ex.getMessage(),
                HttpStatus.UNAUTHORIZED,
                null,
                true);
    }
}