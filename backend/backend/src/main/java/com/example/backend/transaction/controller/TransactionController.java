package com.example.backend.transaction.controller;

import com.example.backend.transaction.dto.ConversionTransactionDTO;
import com.example.backend.transaction.dto.PurchaseTransactionDTO;
import com.example.backend.transaction.dto.TopUpTransactionDTO;
import com.example.backend.transaction.dto.TransactionResponseDTO;
import com.example.backend.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TransactionController class to handle all transaction-related API requests.
 */
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/user/{userId}")
    public List<TransactionResponseDTO> getUserTransactions(@PathVariable String userId) {
        return transactionService.getUserTransactions(userId);
    }

    @PostMapping("/purchase")
    public boolean createPurchaseTransaction(@RequestBody PurchaseTransactionDTO dto) {
        return transactionService.createPurchaseTransaction(dto);
    }

    @PostMapping("/topup")
    public boolean createTopUpTransaction(@RequestBody TopUpTransactionDTO dto) {
        return transactionService.createTopUpTransaction(dto);
    }

    @PostMapping("/conversion")
    public boolean createConversionTransaction(@RequestBody ConversionTransactionDTO dto) {
        return transactionService.createConversionTransaction(dto);
    }
}
