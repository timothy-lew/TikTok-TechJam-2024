package com.example.backend.wallet.repository;

import com.example.backend.wallet.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface WalletRepository extends MongoRepository<Wallet, String> {
    Optional<Wallet> findByUserId(String userId);

    @Query(value = "{ 'walletAddress': { $regex: ?0, $options: 'i' } }", fields = "{ 'userId': 1 }")
    Optional<UserIdOnly> findUserIdByWalletAddressCaseInsensitive(String walletAddress);

    interface UserIdOnly {
        String getUserId();
    }
}
