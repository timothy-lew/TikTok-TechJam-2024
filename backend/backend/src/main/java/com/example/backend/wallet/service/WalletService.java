package com.example.backend.wallet.service;

import com.example.backend.user.model.User;
import com.example.backend.wallet.model.Wallet;
import com.example.backend.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWalletForUser(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setCashBalance(0.0f);
        wallet.setCoinBalance(0.0f);
        return walletRepository.save(wallet);
    }
}
