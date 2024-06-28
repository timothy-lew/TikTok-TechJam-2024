package com.example.backend.common.exception;

public class InvalidGiftCardException extends RuntimeException {
    public InvalidGiftCardException(String message) {
        super(message);
    }
}