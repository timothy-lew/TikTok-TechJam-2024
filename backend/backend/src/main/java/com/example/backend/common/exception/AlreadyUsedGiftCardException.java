package com.example.backend.common.exception;

public class AlreadyUsedGiftCardException extends RuntimeException {
    public AlreadyUsedGiftCardException(String message) {
        super(message);
    }
}
