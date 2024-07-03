package com.example.backend.transaction.controller;

import com.example.backend.auth.model.UserPrincipal;
import com.example.backend.common.controller.BaseController;
import com.example.backend.common.exception.ResourceNotFoundException;
import com.example.backend.transaction.dto.*;
import com.example.backend.transaction.exception.AlreadyUsedGiftCardException;
import com.example.backend.transaction.exception.InsufficientItemQuantityException;
import com.example.backend.transaction.exception.InvalidGiftCardException;
import com.example.backend.transaction.model.GiftCard;
import com.example.backend.transaction.service.GiftCardService;
import com.example.backend.transaction.service.TransactionService;
import com.example.backend.wallet.exception.InsufficientWalletCashBalance;
import com.example.backend.wallet.exception.InsufficientWalletTokenBalance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TransactionController class to handle all transaction-related API requests.
 */
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController extends BaseController {

    private final TransactionService transactionService;
    private final GiftCardService giftCardService;


    @GetMapping("/buyer/{buyerProfileId}")
    public ResponseEntity<?> getBuyerTransactions(@PathVariable String buyerProfileId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userNotBuyer(userPrincipal)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not a buyer");
        }
        try {
            List<TransactionResponseDTO> transactions = transactionService.getBuyerTransactions(buyerProfileId);
            return ResponseEntity.ok(transactions);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching buyer transactions for buyerProfileId: {}", buyerProfileId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/seller/{sellerProfileId}")
    public ResponseEntity<?> getSellerTransactions(@PathVariable String sellerProfileId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userNotSeller(userPrincipal)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not a seller");
        }
        try {
            List<TransactionResponseDTO> transactions = transactionService.getSellerTransactions(sellerProfileId);
            return ResponseEntity.ok(transactions);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching seller transactions for sellerProfileId: {}", sellerProfileId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> createPurchaseTransaction(@RequestBody PurchaseTransactionDTO dto) {
        try {
            TransactionResponseDTO response = transactionService.createPurchaseTransaction(dto);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InsufficientItemQuantityException | InsufficientWalletCashBalance |
                 InsufficientWalletTokenBalance e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error creating purchase transaction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/topup")
    public ResponseEntity<?> createTopUpTransaction(@RequestBody TopUpTransactionDTO dto) {
        try {
            TransactionResponseDTO responseDTO = transactionService.createTopUpTransaction(dto);
            return ResponseEntity.ok(responseDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidGiftCardException | AlreadyUsedGiftCardException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error creating top-up transaction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/conversion")
    public ResponseEntity<?> createConversionTransaction(@RequestBody ConversionTransactionDTO dto) {
        try {
            TransactionResponseDTO response = transactionService.createConversionTransaction(dto);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InsufficientWalletCashBalance | InsufficientWalletTokenBalance e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error creating conversion transaction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> createWithdrawTransaction(@RequestBody WithdrawTransactionDTO dto) {
        try {
            TransactionResponseDTO response = transactionService.createWithdrawTransaction(dto);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InsufficientWalletCashBalance e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error creating withdraw transaction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/status/{transactionId}")
    public ResponseEntity<?> checkTransactionStatus(@PathVariable String transactionId) {
        try {
            Boolean isPaid = transactionService.checkTransactionStatus(transactionId);
            return ResponseEntity.ok(isPaid);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error checking transaction status for transactionId: {}", transactionId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    // Sole purpose to just provide a way to create gift cards, not required in application
    @PostMapping("/giftcard")
    public ResponseEntity<List<GiftCard>> createGiftCards(@RequestBody List<GiftCardDTO> giftCardDTOs) {
        List<GiftCard> createdGiftCards = new ArrayList<>();
        for (GiftCardDTO dto : giftCardDTOs) {
            GiftCard giftCard = giftCardService.createGiftCard(dto.getCode(), dto.getValue());
            createdGiftCards.add(giftCard);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGiftCards);
    }

    // Not required in application, just for testing purposes
    @GetMapping("/giftcard")
    public ResponseEntity<List<GiftCard>> getAllGiftCards() {
        List<GiftCard> giftCards = giftCardService.getAllGiftCards();
        return ResponseEntity.ok(giftCards);
    }

}
