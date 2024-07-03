package com.example.backend.wallet.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.contract.dto.SendCryptoDTO;
import com.example.backend.contract.service.ContractService;
import com.example.backend.transaction.model.Transaction;
import com.example.backend.wallet.exception.InsufficientWalletCashBalance;
import com.example.backend.wallet.exception.InsufficientWalletTokenBalance;
import com.example.backend.wallet.model.Wallet;
import com.example.backend.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final ContractService contractService;
    private final CommonValidationAndGetService commonValidationAndGetService;

    public Wallet createWallet(String userId, String walletAddress) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setWalletAddress(walletAddress);
        wallet.setCashBalance(BigDecimal.ZERO);
        return walletRepository.save(wallet);
    }

    // Handles deduction of buyer's and addition of seller's balance.
    // Depending on purchaseType, handles cash and toktoken deduction/addition differently.
    // Cash balance is deducted/added automatically from the respective buyer's and seller's Wallet entity.
    // Toktoken balance is deducted/added manually, through the crypto plugin, send the amount from buyer's to seller's wallet address.
    public void handlePurchase(String buyerProfileId, String sellerProfileId, BigDecimal amount, Transaction.PurchaseType purchaseType) {
        Wallet buyerWallet = commonValidationAndGetService.validateAndGetWalletByBuyerProfileId(buyerProfileId);
        Wallet sellerWallet = commonValidationAndGetService.validateAndGetWalletBySellerProfileId(sellerProfileId);

        if (purchaseType == Transaction.PurchaseType.CASH) {
            if (buyerWallet.getCashBalance().compareTo(amount) < 0) {
                throw new InsufficientWalletCashBalance("Insufficient cash balance");
            }
            buyerWallet.setCashBalance(buyerWallet.getCashBalance().subtract(amount));
            sellerWallet.setCashBalance(sellerWallet.getCashBalance().add(amount));
            walletRepository.save(buyerWallet);
            walletRepository.save(sellerWallet);
        } else if (purchaseType == Transaction.PurchaseType.TOK_TOKEN) {
            if (contractService.getBalance(buyerWallet.getWalletAddress()).compareTo(amount) < 0) {
                throw new InsufficientWalletTokenBalance("Insufficient token balance");
            }
            contractService.listenToSellerAddress(sellerWallet.getWalletAddress());
            // Then manually, through the crypto plugin, send the amount to tiktok's wallet address
        }
    }

    // Handles top-up of user's cash balance ONLY, not toktoken balance.
    public void handleTopUp(String userId, BigDecimal amount) {
        Wallet wallet = commonValidationAndGetService.validateAndGetWalletByUserId(userId);
        wallet.setCashBalance(wallet.getCashBalance().add(amount));
        walletRepository.save(wallet);
    }

    // Handles conversion between cash and toktoken.
    public void handleConvert(String userId, BigDecimal originalAmount, BigDecimal convertedAmount, Transaction.ConversionType conversionType) {
        Wallet wallet = commonValidationAndGetService.validateAndGetWalletByUserId(userId);

        if (conversionType == Transaction.ConversionType.CASH_TO_TOKTOKEN) {
            if (wallet.getCashBalance().compareTo(originalAmount) < 0) {
                throw new InsufficientWalletCashBalance("Insufficient cash balance");
            }
            wallet.setCashBalance(wallet.getCashBalance().subtract(originalAmount));
            contractService.sendCrypto(new SendCryptoDTO(wallet.getWalletAddress(), convertedAmount.longValue()));
        } else if (conversionType == Transaction.ConversionType.TOKTOKEN_TO_CASH) {
            BigDecimal tokTokenBalance = contractService.getBalance(wallet.getWalletAddress());
            if (tokTokenBalance.compareTo(originalAmount) < 0) {
                throw new InsufficientWalletTokenBalance("Insufficient token balance");
            }
            contractService.listenToTikTokAddress();
            // Then manually, through the crypto plugin, send the amount to tiktok's wallet address
            wallet.setCashBalance(wallet.getCashBalance().add(convertedAmount));
        }

        walletRepository.save(wallet);
    }

    public void handleWithdraw(String userId, BigDecimal amount) {
        Wallet wallet = commonValidationAndGetService.validateAndGetWalletByUserId(userId);

        BigDecimal currentBalance = wallet.getCashBalance();

        if (currentBalance.compareTo(amount) < 0) {
            throw new InsufficientWalletCashBalance("Insufficient cash balance");
        }

        wallet.setCashBalance(currentBalance.subtract(amount));
        walletRepository.save(wallet);
    }

}
