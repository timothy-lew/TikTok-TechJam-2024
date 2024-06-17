package com.example.backend.common.exception;

public class InvalidPrincipalException extends RuntimeException {
    public InvalidPrincipalException(String errorMessage) {
        super(errorMessage);
    }
}
