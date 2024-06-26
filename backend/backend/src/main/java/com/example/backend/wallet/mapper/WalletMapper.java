package com.example.backend.wallet.mapper;

import com.example.backend.contract.service.ContractService;
import com.example.backend.wallet.dto.WalletResponseDTO;
import com.example.backend.wallet.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public abstract class WalletMapper {
    @Autowired
    private ContractService contractService;

    @Mapping(target = "tokTokenBalance", source = "walletAddress", qualifiedByName = "getTokTokenBalance")
    public abstract WalletResponseDTO fromWallettoWalletResponseDTO(Wallet wallet);

    @Named("getTokTokenBalance")
    BigDecimal getTokTokenBalance(String walletAddress) {
        return contractService.getBalance(walletAddress);
    }
}
