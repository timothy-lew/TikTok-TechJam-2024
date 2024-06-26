package com.example.backend.wallet.service;

import com.example.backend.contract.dto.SendCryptoDTO;
import com.example.backend.contract.service.ContractService;
import com.example.backend.transaction.model.Transaction;
import com.example.backend.user.model.BuyerProfile;
import com.example.backend.user.model.SellerProfile;
import com.example.backend.user.repository.BuyerProfileRepository;
import com.example.backend.user.repository.SellerProfileRepository;
import com.example.backend.wallet.model.Wallet;
import com.example.backend.wallet.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final BuyerProfileRepository buyerProfileRepository;
    private final SellerProfileRepository sellerProfileRepository;
    private final ContractService contractService;

    public WalletService(WalletRepository walletRepository, ContractService contractService, SellerProfileRepository sellerProfileRepository, BuyerProfileRepository buyerProfileRepository) {
        this.walletRepository = walletRepository;
        this.buyerProfileRepository = buyerProfileRepository;
        this.sellerProfileRepository = sellerProfileRepository;
        this.contractService = contractService;
    }

    public Wallet createWallet(String userId, String walletAddress) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setWalletAddress(walletAddress);
        wallet.setCashBalance(BigDecimal.ZERO);
        return walletRepository.save(wallet);
    }

    // Handles deduction of buyer's and addition of seller's balance.
    // Depending on purchaseType, handles cash and toktoken deduction/addition differently.
    // Cash balance is deducted/added directly from the respective buyer's and seller's Wallet entity.
    // Toktoken balance is deducted/added by manually, through the crypto plugin, send the amount to the seller's wallet address.
    // To check for the validity of the transaction through the crypto plugin, we call the sendCrypto method to listen to a queue from the blockchain.
    public void handlePurchase(String buyerProfileId, String sellerProfileId, BigDecimal amount, Transaction.PurchaseType purchaseType) {
        BuyerProfile buyerProfile = buyerProfileRepository.findById(buyerProfileId)
                .orElseThrow(() -> new RuntimeException("Buyer profile not found"));

        Wallet buyerWallet = walletRepository.findByUserId(buyerProfile.getUserId())
                .orElseThrow(() -> new RuntimeException("Buyer wallet not found"));

        SellerProfile sellerProfile = sellerProfileRepository.findById(sellerProfileId)
                .orElseThrow(() -> new RuntimeException("Seller profile not found"));

        Wallet sellerWallet = walletRepository.findByUserId(sellerProfile.getUserId())
                .orElseThrow(() -> new RuntimeException("Seller wallet not found"));

        if (purchaseType == Transaction.PurchaseType.CASH) {
            if (buyerWallet.getCashBalance().compareTo(amount) < 0) {
                throw new RuntimeException("Insufficient cash balance");
            }
            buyerWallet.setCashBalance(buyerWallet.getCashBalance().subtract(amount));
            sellerWallet.setCashBalance(sellerWallet.getCashBalance().add(amount));
            walletRepository.save(buyerWallet);
            walletRepository.save(sellerWallet);
        } else if (purchaseType == Transaction.PurchaseType.TOK_TOKEN) {
            if (contractService.getBalance(buyerWallet.getWalletAddress()).compareTo(amount) < 0) {
                throw new RuntimeException("Insufficient TokToken balance");
            }
            // TODO: Call contractService.listen(sellerWallet.getWalletAddress())
//            while (contractService.listen(sellerWallet.getWalletAddress())) {
//              e.g., if buyer sends successfully, then break the loop and return true, and continue with the rest of the code in transactionService.
//            }
            // Then manually, through the crypto plugin, send the amount to the seller's wallet address.
        }
    }

    // Handles top-up of user's cash balance ONLY, not toktoken balance.
    public void handleTopUp(String userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        // TODO: Handle gift card and credit card top-up types
        wallet.setCashBalance(wallet.getCashBalance().add(amount));
        walletRepository.save(wallet);
    }

    // Handles conversion between cash and toktoken.
    public void handleConvert(String userId, BigDecimal originalAmount, BigDecimal convertedAmount, Transaction.ConversionType conversionType) {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (conversionType == Transaction.ConversionType.CASH_TO_TOKTOKEN) {
            if (wallet.getCashBalance().compareTo(originalAmount) < 0) {
                throw new RuntimeException("Insufficient cash balance");
            }
            wallet.setCashBalance(wallet.getCashBalance().subtract(originalAmount));
            contractService.sendCrypto(new SendCryptoDTO(wallet.getWalletAddress(), convertedAmount.longValue()));
        } else if (conversionType == Transaction.ConversionType.TOKTOKEN_TO_CASH) {
            BigDecimal tokTokenBalance = contractService.getBalance(wallet.getWalletAddress());
            if (tokTokenBalance.compareTo(originalAmount) < 0) {
                throw new RuntimeException("Insufficient token balance");
            }
            // TODO: Call contractService.listen(), a separate method, to listen on tiktok address (hardcoded address, not dynamic)
            // Then manually, through the crypto plugin, send the amount to tiktok's wallet address
            wallet.setCashBalance(wallet.getCashBalance().add(convertedAmount));
        }

        walletRepository.save(wallet);
    }


}
