package com.example.backend.transaction.exception;

public class AlreadyUsedGiftCardException extends RuntimeException {
    public AlreadyUsedGiftCardException(String message) {
        super(message);
    }
}
