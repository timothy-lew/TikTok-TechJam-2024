package com.example.backend.common.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
