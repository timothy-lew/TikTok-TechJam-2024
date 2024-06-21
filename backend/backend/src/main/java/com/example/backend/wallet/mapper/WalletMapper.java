package com.example.backend.wallet.mapper;

import com.example.backend.wallet.dto.WalletResponseDTO;
import com.example.backend.wallet.model.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletResponseDTO fromWallettoWalletResponseDTO(Wallet wallet);
}
