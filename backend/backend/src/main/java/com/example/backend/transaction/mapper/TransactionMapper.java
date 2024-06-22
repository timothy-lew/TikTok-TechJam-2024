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
                transaction.getQuantity()
        );
    }

    @Named("toTopUpDetails")
    default TransactionResponseDTO.TopUpDetails toTopUpDetails(Transaction transaction) {
        if (transaction.getTransactionType() != Transaction.TransactionType.TOPUP) {
            return null;
        }
        return new TransactionResponseDTO.TopUpDetails(
                transaction.getTopUpTransactionType() != null ? transaction.getTopUpTransactionType().toString() : null,
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
                transaction.getCoinBalance()
        );
    }

    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "transactionType", constant = "CONVERSION")
    Transaction fromDTOtoTransaction(ConversionTransactionDTO dto);

    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "transactionType", constant = "TOPUP")
    Transaction fromDTOtoTransaction(TopUpTransactionDTO dto);

    @Mapping(target = "transactionDate", ignore = true)
    @Mapping(target = "transactionType", constant = "PURCHASE")
    @Mapping(target = "userId", constant = "null")
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    Transaction fromDTOtoTransaction(PurchaseTransactionDTO dto);
}