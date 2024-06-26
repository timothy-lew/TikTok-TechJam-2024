package com.example.backend.wallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Wallet class to store the wallet details of the user.
 * One-to-one relationship with User entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "wallets")
public class Wallet {

    @Id
    private String id;
    private String userId;
    private BigDecimal cashBalance;
    private String walletAddress;
}
