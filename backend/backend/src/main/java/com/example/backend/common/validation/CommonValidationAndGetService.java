package com.example.backend.common.validation;

import com.example.backend.common.exception.ResourceNotFoundException;
import com.example.backend.item.model.Item;
import com.example.backend.item.repository.ItemRepository;
import com.example.backend.rates.model.ConversionRate;
import com.example.backend.rates.repository.ConversionRateRepository;
import com.example.backend.transaction.model.Transaction;
import com.example.backend.transaction.repository.TransactionRepository;
import com.example.backend.user.model.BuyerProfile;
import com.example.backend.user.model.SellerProfile;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.BuyerProfileRepository;
import com.example.backend.user.repository.SellerProfileRepository;
import com.example.backend.user.repository.UserRepository;
import com.example.backend.wallet.model.Wallet;
import com.example.backend.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommonValidationAndGetService {

    private final UserRepository userRepository;
    private final BuyerProfileRepository buyerProfileRepository;
    private final SellerProfileRepository sellerProfileRepository;
    private final ItemRepository itemRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final ConversionRateRepository conversionRateRepository;

    public User validateAndGetUser(String userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId " + userId));
    }

    public User validateAndGetUserByBuyerProfileId(String buyerProfileId) throws ResourceNotFoundException {
        return userRepository.findByBuyerProfileId(buyerProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with buyerProfileId " + buyerProfileId));
    }

    public BuyerProfile validateAndGetBuyerProfile(String buyerProfileId) {
        return buyerProfileRepository.findById(buyerProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer profile not found with id: " + buyerProfileId));
    }

    public BuyerProfile validateAndGetBuyerProfileByUserId(String userId) throws ResourceNotFoundException {
        return buyerProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer profile not found with userId " + userId));
    }

    public SellerProfile validateAndGetSellerProfile(String sellerProfileId) {
        return sellerProfileRepository.findById(sellerProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller profile not found with id: " + sellerProfileId));
    }

    public SellerProfile validateAndGetSellerProfileByUserId(String userId) throws ResourceNotFoundException {
        return sellerProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller profile not found with userId " + userId));
    }

    // Items
    public List<Item> validateAndGetAllItems() throws ResourceNotFoundException {
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty()) {
            throw new ResourceNotFoundException("No items found");
        }
        return items;
    }

    public Item validateAndGetItem(String itemId) throws ResourceNotFoundException {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));
    }

    public List<Item> validateAndGetItemsByUserId(String userId) throws ResourceNotFoundException {
        SellerProfile sellerProfile = validateAndGetSellerProfileByUserId(userId);
        List<Item> items = itemRepository.findBySellerProfileId(sellerProfile.getId());
        if (items.isEmpty()) {
            throw new ResourceNotFoundException("No items found for userId: " + userId);
        }
        return items;
    }

    public List<Item> validateAndGetItemsBySellerProfileId(String sellerProfileId) throws ResourceNotFoundException {
        List<Item> items = itemRepository.findBySellerProfileId(sellerProfileId);
        if (items.isEmpty()) {
            throw new ResourceNotFoundException("No items found for sellerProfileId: " + sellerProfileId);
        }
        return items;
    }

    // Transaction
    public List<Transaction> validateAndGetTransactionsByBuyerProfileId(String buyerProfileId) throws ResourceNotFoundException {
        List<Transaction> transactions = transactionRepository.findByBuyerProfileId(buyerProfileId);
        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for buyerProfileId: " + buyerProfileId);
        }
        return transactions;
    }

    public List<Transaction> validateAndGetTransactionsBySellerProfileId(String sellerProfileId) throws ResourceNotFoundException {
        List<Transaction> transactions = transactionRepository.findBySellerProfileId(sellerProfileId);
        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for sellerProfileId: " + sellerProfileId);
        }
        return transactions;
    }

    public Transaction validateAndGetTransaction(String transactionId) throws ResourceNotFoundException {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + transactionId));
    }

    // Wallet
    public Wallet validateAndGetWalletByUserId(String userId) throws ResourceNotFoundException {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with userId: " + userId));
    }

    public Wallet validateAndGetWalletByBuyerProfileId(String buyerProfileId) throws ResourceNotFoundException {
        BuyerProfile buyerProfile = validateAndGetBuyerProfile(buyerProfileId);
        return walletRepository.findByUserId(buyerProfile.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with buyerProfileId: " + buyerProfileId));
    }

    public Wallet validateAndGetWalletBySellerProfileId(String sellerProfileId) throws ResourceNotFoundException {
        SellerProfile sellerProfile = validateAndGetSellerProfile(sellerProfileId);
        return walletRepository.findByUserId(sellerProfile.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with sellerProfileId: " + sellerProfileId));
    }

    // Conversion rate
    public ConversionRate validateAndGetCurrentConversionRate() throws ResourceNotFoundException {
        List<ConversionRate> rates = conversionRateRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
        if (rates.isEmpty()) {
            throw new RuntimeException("Conversion rate not set");
        }
        return rates.get(0); // Return the latest conversion rate
    }

}
