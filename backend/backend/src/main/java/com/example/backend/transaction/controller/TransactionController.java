package com.example.backend.transaction.controller;

import com.example.backend.auth.model.UserPrincipal;
import com.example.backend.common.controller.BaseController;
import com.example.backend.transaction.dto.*;
import com.example.backend.transaction.model.GiftCard;
import com.example.backend.transaction.service.GiftCardService;
import com.example.backend.transaction.service.TransactionService;
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
@Slf4j
public class TransactionController extends BaseController {

    private final TransactionService transactionService;
    private final GiftCardService giftCardService;

    public TransactionController(TransactionService transactionService, GiftCardService giftCardService) {
        this.transactionService = transactionService;
        this.giftCardService = giftCardService;
    }

    @GetMapping("/buyer/{buyerProfileId}")
    public ResponseEntity<List<TransactionResponseDTO>> getBuyerTransactions(@PathVariable String buyerProfileId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userNotBuyer(userPrincipal)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<TransactionResponseDTO> transactions = transactionService.getBuyerTransactions(buyerProfileId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/seller/{sellerProfileId}")
    public ResponseEntity<List<TransactionResponseDTO>> getSellerTransactions(@PathVariable String sellerProfileId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userNotSeller(userPrincipal)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<TransactionResponseDTO> transactions = transactionService.getSellerTransactions(sellerProfileId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/purchase")
    public ResponseEntity<TransactionResponseDTO> createPurchaseTransaction(@RequestBody PurchaseTransactionDTO dto) {
        TransactionResponseDTO response = transactionService.createPurchaseTransaction(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/topup")
    public ResponseEntity<TransactionResponseDTO> createTopUpTransaction(@RequestBody TopUpTransactionDTO dto) {
        TransactionResponseDTO response = transactionService.createTopUpTransaction(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/conversion")
    public ResponseEntity<TransactionResponseDTO> createConversionTransaction(@RequestBody ConversionTransactionDTO dto) {
        TransactionResponseDTO response = transactionService.createConversionTransaction(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponseDTO> createWithdrawTransaction(@RequestBody WithdrawTransactionDTO dto) {
        TransactionResponseDTO response = transactionService.createWithdrawTransaction(dto);
        return ResponseEntity.ok(response);
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
