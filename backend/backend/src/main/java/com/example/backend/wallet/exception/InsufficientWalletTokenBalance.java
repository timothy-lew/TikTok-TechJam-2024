package com.example.backend.wallet.exception;

public class InsufficientWalletTokenBalance extends RuntimeException {
    public InsufficientWalletTokenBalance(String message) {
        super(message);
    }
}
