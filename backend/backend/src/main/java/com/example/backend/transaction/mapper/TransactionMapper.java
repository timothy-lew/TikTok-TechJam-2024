package com.example.backend.transaction.mapper;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.transaction.dto.*;
import com.example.backend.transaction.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class TransactionMapper {

    @Autowired
    private CommonValidationAndGetService commonValidationAndGetService;

    @Mapping(target = "purchaseDetails", source = ".", qualifiedByName = "toPurchaseDetails")
    @Mapping(target = "topUpDetails", source = ".", qualifiedByName = "toTopUpDetails")
    @Mapping(target = "conversionDetails", source = ".", qualifiedByName = "toConversionDetails")
    @Mapping(target = "withdrawDetails", source = ".", qualifiedByName = "toWithdrawDetails")
    public abstract TransactionResponseDTO fromTransactiontoTransactionResponseDTO(Transaction transaction);

    @Named("toPurchaseDetails")
    @Mapping(target = "buyerUserName", ignore = true)
    @Mapping(target = "sellerBusinessName", ignore = true)
    TransactionResponseDTO.PurchaseDetails toPurchaseDetails(Transaction transaction) {
        if (transaction.getTransactionType() != Transaction.TransactionType.PURCHASE) {
            return null;
        }
        return new TransactionResponseDTO.PurchaseDetails(
                transaction.getBuyerProfileId(),
                commonValidationAndGetService.validateAndGetUserByBuyerProfileId(transaction.getBuyerProfileId()).getUsername(),
                transaction.getSellerProfileId(),
                commonValidationAndGetService.validateAndGetSellerProfile(transaction.getSellerProfileId()).getBusinessName(),
                transaction.getItemId(),
                transaction.getQuantity(),
                transaction.getTotalAmount(),
                transaction.getPurchaseType() != null ? transaction.getPurchaseType().toString() : null
        );
    }

    @Named("toTopUpDetails")
    TransactionResponseDTO.TopUpDetails toTopUpDetails(Transaction transaction) {
        if (transaction.getTransactionType() != Transaction.TransactionType.TOPUP) {
            return null;
        }
        return new TransactionResponseDTO.TopUpDetails(
                transaction.getTopUpType() != null ? transaction.getTopUpType().toString() : null,
                transaction.getTopUpAmount()
        );
    }

    @Named("toConversionDetails")
    TransactionResponseDTO.ConversionDetails toConversionDetails(Transaction transaction) {
        if (transaction.getTransactionType() != Transaction.TransactionType.CONVERSION) {
            return null;
        }
        return new TransactionResponseDTO.ConversionDetails(
                transaction.getConversionRate(),
                transaction.getCashToConvert(),
                transaction.getTokTokenToConvert(),
                transaction.getConvertedAmount(),
                transaction.getConversionType().toString()
        );
    }

    @Named("toWithdrawDetails")
    TransactionResponseDTO.WithdrawDetails toWithdrawDetails(Transaction transaction) {
        if (transaction.getTransactionType() != Transaction.TransactionType.WITHDRAW) {
            return null;
        }
        return new TransactionResponseDTO.WithdrawDetails(
                transaction.getWithdrawAmount()
        );
    }

    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "transactionType", expression = "java(com.example.backend.transaction.model.Transaction.TransactionType.CONVERSION)")
    @Mapping(target = "isPaid", ignore = true)
    @Mapping(target = "conversionRate", ignore = true)
    @Mapping(target = "cashToConvert", ignore = true)
    @Mapping(target = "tokTokenToConvert", ignore = true)
    @Mapping(target = "convertedAmount", ignore = true)
    @Mapping(target = "conversionType", source = "conversionType", qualifiedByName = "toConversionType")
    public abstract Transaction fromDTOtoTransaction(ConversionTransactionDTO dto);

    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "transactionType", expression = "java(com.example.backend.transaction.model.Transaction.TransactionType.TOPUP)")
    @Mapping(target = "isPaid", ignore = true)
    @Mapping(target = "topUpType", source = "topUpType", qualifiedByName = "toTopUpType")
    @Mapping(target = "topUpAmount", ignore = true)
    @Mapping(target = "giftCardCode", ignore = true)
    public abstract Transaction fromDTOtoTransaction(TopUpTransactionDTO dto);

    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "transactionType", expression = "java(com.example.backend.transaction.model.Transaction.TransactionType.PURCHASE)")
    @Mapping(target = "isPaid", ignore = true)
    @Mapping(target = "userId", constant = "null")
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "purchaseType", source = "purchaseType", qualifiedByName = "toPurchaseType")
    public abstract Transaction fromDTOtoTransaction(PurchaseTransactionDTO dto);

    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "transactionType", expression = "java(com.example.backend.transaction.model.Transaction.TransactionType.WITHDRAW)")
    @Mapping(target = "isPaid", ignore = true)
    public abstract Transaction fromDTOtoTransaction(WithdrawTransactionDTO dto);

    @Named("toConversionType")
    Transaction.ConversionType toConversionType(String conversionType) {
        if (conversionType == null) {
            return null;
        }
        return Transaction.ConversionType.valueOf(conversionType);
    }

    @Named("toTopUpType")
    Transaction.TopUpType toTopUpType(String topUpType) {
        if (topUpType == null) {
            return null;
        }
        return Transaction.TopUpType.valueOf(topUpType);
    }

    @Named("toPurchaseType")
    Transaction.PurchaseType toPurchaseType(String purchaseType) {
        if (purchaseType == null) {
            return null;
        }
        return Transaction.PurchaseType.valueOf(purchaseType);
    }
}