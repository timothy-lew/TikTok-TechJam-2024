package com.example.backend.wallet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletResponseDTO {
    private String id;
    private Float cashBalance;
    private Float coinBalance;
}
