package com.example.backend.transaction.exception;

public class InvalidGiftCardException extends RuntimeException {
    public InvalidGiftCardException(String message) {
        super(message);
    }
}