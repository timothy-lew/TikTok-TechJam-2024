package com.example.backend.transaction.mapper;

import com.example.backend.transaction.dto.ConversionTransactionDTO;
import com.example.backend.transaction.dto.PurchaseTransactionDTO;
import com.example.backend.transaction.dto.TopUpTransactionDTO;
import com.example.backend.transaction.dto.TransactionResponseDTO;
import com.example.backend.transaction.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TransactionMapper {

    @Mapping(target = "purchaseDetails", source = ".", qualifiedByName = "toPurchaseDetails")
    @Mapping(target = "topUpDetails", source = ".", qualifiedByName = "toTopUpDetails")
    @Mapping(target = "conversionDetails", source = ".", qualifiedByName = "toConversionDetails")
    TransactionResponseDTO fromTransactiontoTransactionResponseDTO(Transaction transaction);

    @Named("toPurchaseDetails")
    default TransactionResponseDTO.PurchaseDetails toPurchaseDetails(Transaction transaction) {
        if (transaction.getTransactionType() != Transaction.TransactionType.PURCHASE) {
            return null;
        }
        return new TransactionResponseDTO.PurchaseDetails(
                transaction.getBuyerProfileId(),
                transaction.getSellerProfileId(),
                transaction.getItemId(),
                transaction.getQuantity(),
                transaction.getPurchaseType() != null ? transaction.getPurchaseType().toString() : null
        );
    }

    @Named("toTopUpDetails")
    default TransactionResponseDTO.TopUpDetails toTopUpDetails(Transaction transaction) {
        if (transaction.getTransactionType() != Transaction.TransactionType.TOPUP) {
            return null;
        }
        return new TransactionResponseDTO.TopUpDetails(
                transaction.getTopUpType() != null ? transaction.getTopUpType().toString() : null,
                transaction.getTopUpAmount()
        );
    }

    @Named("toConversionDetails")
    default TransactionResponseDTO.ConversionDetails toConversionDetails(Transaction transaction) {
        if (transaction.getTransactionType() != Transaction.TransactionType.CONVERSION) {
            return null;
        }
        return new TransactionResponseDTO.ConversionDetails(
                transaction.getConversionRate(),
                transaction.getCashBalance(),
                transaction.getTokTokenBalance(),
                transaction.getConversionType() != null ? transaction.getConversionType().toString() : null
        );
    }

    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "transactionType", expression = "java(com.example.backend.transaction.model.Transaction.TransactionType.CONVERSION)")
    @Mapping(target = "cashBalance", ignore = true)
    @Mapping(target = "tokTokenBalance", ignore = true)
    @Mapping(target = "conversionType", source = "conversionType", qualifiedByName = "toConversionType")
    Transaction fromDTOtoTransaction(ConversionTransactionDTO dto);

    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "transactionType", expression = "java(com.example.backend.transaction.model.Transaction.TransactionType.TOPUP)")
    @Mapping(target = "topUpType", source = "topUpType", qualifiedByName = "toTopUpType")
    Transaction fromDTOtoTransaction(TopUpTransactionDTO dto);

    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "transactionType", expression = "java(com.example.backend.transaction.model.Transaction.TransactionType.PURCHASE)")
    @Mapping(target = "userId", constant = "null")
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "purchaseType", source = "purchaseType", qualifiedByName = "toPurchaseType")
    Transaction fromDTOtoTransaction(PurchaseTransactionDTO dto);

    @Named("toConversionType")
    default Transaction.ConversionType toConversionType(String conversionType) {
        if (conversionType == null) {
            return null;
        }
        return Transaction.ConversionType.valueOf(conversionType);
    }

    @Named("toTopUpType")
    default Transaction.TopUpType toTopUpType(String topUpType) {
        if (topUpType == null) {
            return null;
        }
        return Transaction.TopUpType.valueOf(topUpType);
    }

    @Named("toPurchaseType")
    default Transaction.PurchaseType toPurchaseType(String purchaseType) {
        if (purchaseType == null) {
            return null;
        }
        return Transaction.PurchaseType.valueOf(purchaseType);
    }
}