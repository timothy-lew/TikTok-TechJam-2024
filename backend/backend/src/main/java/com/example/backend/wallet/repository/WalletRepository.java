package com.example.backend.wallet.repository;

import com.example.backend.wallet.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WalletRepository extends MongoRepository<Wallet, String> {
}
