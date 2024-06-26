package com.example.backend.wallet.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WalletResponseDTO {
    private String id;
    private String userId;
    private BigDecimal cashBalance;
    private String walletAddress;
    private BigDecimal tokTokenBalance;
}
