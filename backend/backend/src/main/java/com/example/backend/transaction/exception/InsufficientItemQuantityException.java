package com.example.backend.transaction.exception;

public class InsufficientItemQuantityException extends RuntimeException {
    public InsufficientItemQuantityException(String message) {
        super(message);
    }
}