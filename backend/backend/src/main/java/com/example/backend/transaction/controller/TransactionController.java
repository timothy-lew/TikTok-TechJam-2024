package com.example.backend.transaction.controller;

import com.example.backend.auth.model.UserPrincipal;
import com.example.backend.common.controller.BaseController;
import com.example.backend.transaction.dto.ConversionTransactionDTO;
import com.example.backend.transaction.dto.PurchaseTransactionDTO;
import com.example.backend.transaction.dto.TopUpTransactionDTO;
import com.example.backend.transaction.dto.TransactionResponseDTO;
import com.example.backend.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TransactionController class to handle all transaction-related API requests.
 */
@RestController
@RequestMapping("/api/transactions")
@Slf4j
public class TransactionController extends BaseController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
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

    // TODO: Potentially removing this endpoint and dto, since wallet controller will handle this, only provide service method to create transaction.
    @PostMapping("/conversion")
    public ResponseEntity<TransactionResponseDTO> createConversionTransaction(@RequestBody ConversionTransactionDTO dto) {
        TransactionResponseDTO response = transactionService.createConversionTransaction(dto);
        return ResponseEntity.ok(response);
    }
}
