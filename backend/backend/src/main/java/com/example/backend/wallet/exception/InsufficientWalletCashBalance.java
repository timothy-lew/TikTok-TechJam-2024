package com.example.backend.wallet.exception;

public class InsufficientWalletCashBalance extends RuntimeException {
    public InsufficientWalletCashBalance(String message) {
        super(message);
    }
}
