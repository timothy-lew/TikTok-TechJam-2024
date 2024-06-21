package com.example.backend.transaction.mapper;

import com.example.backend.transaction.dto.ConversionTransactionDTO;
import com.example.backend.transaction.dto.PurchaseTransactionDTO;
import com.example.backend.transaction.dto.TopUpTransactionDTO;
import com.example.backend.transaction.dto.TransactionResponseDTO;
import com.example.backend.transaction.model.ConversionTransaction;
import com.example.backend.transaction.model.PurchaseTransaction;
import com.example.backend.transaction.model.TopUpTransaction;
import com.example.backend.transaction.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponseDTO fromTransactiontoTransactionResponseDTO(Transaction transaction) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setId(transaction.getId());
        dto.setTransactionType(transaction.getTransactionType().toString());
        dto.setTransactionDate(transaction.getTransactionDate().toString());
        dto.setUserId(transaction.getUserId());

        if (transaction instanceof PurchaseTransaction) {
            PurchaseTransaction pt = (PurchaseTransaction) transaction;
            dto.setBuyerProfileId(pt.getBuyerProfileId());
            dto.setSellerProfileId(pt.getSellerProfileId());
            dto.setItemId(pt.getItemId());
            dto.setPrice(pt.getPrice());
            dto.setQuantity(pt.getQuantity());
            dto.setTotalAmount(pt.getTotalAmount());
        } else if (transaction instanceof TopUpTransaction) {
            TopUpTransaction tt = (TopUpTransaction) transaction;
            dto.setTopUpAmount(tt.getTopUpAmount());
            dto.setTopUpTransactionType(tt.getTopUpTransactionType().toString());
        } else if (transaction instanceof ConversionTransaction) {
            ConversionTransaction ct = (ConversionTransaction) transaction;
            dto.setConversionRate(ct.getConversionRate());
            dto.setCashBalance(ct.getCashBalance());
            dto.setCoinBalance(ct.getCoinBalance());
        }

        return dto;
    }

    public Transaction toEntity(PurchaseTransactionDTO dto) {
        PurchaseTransaction pt = new PurchaseTransaction();
        pt.setUserId(dto.getUserId());
        pt.setBuyerProfileId(dto.getBuyerProfileId());
        pt.setSellerProfileId(dto.getSellerProfileId());
        pt.setItemId(dto.getItemId());
        pt.setPrice(dto.getPrice());
        pt.setQuantity(dto.getQuantity());
        pt.setTotalAmount(dto.getTotalAmount());
        pt.setTransactionType(Transaction.TransactionType.PURCHASE);
        return pt;
    }

    public Transaction toEntity(TopUpTransactionDTO dto) {
        TopUpTransaction tt = new TopUpTransaction();
        tt.setUserId(dto.getUserId());
        tt.setTopUpAmount(dto.getTopUpAmount());
        tt.setTopUpTransactionType(TopUpTransaction.TopUpTransactionType.valueOf(dto.getTopUpTransactionType()));
        tt.setTransactionType(Transaction.TransactionType.TOPUP);
        return tt;
    }

    public Transaction toEntity(ConversionTransactionDTO dto) {
        ConversionTransaction ct = new ConversionTransaction();
        ct.setUserId(dto.getUserId());
        ct.setConversionRate(dto.getConversionRate());
        ct.setCashBalance(dto.getCashBalance());
        ct.setCoinBalance(dto.getCoinBalance());
        ct.setTransactionType(Transaction.TransactionType.CONVERSION);
        return ct;
    }
}
