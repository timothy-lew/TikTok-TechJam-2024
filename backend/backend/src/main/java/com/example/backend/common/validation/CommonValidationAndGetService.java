package com.example.backend.common.validation;

import com.example.backend.common.exception.ResourceNotFoundException;
import com.example.backend.item.model.Item;
import com.example.backend.item.repository.ItemRepository;
import com.example.backend.user.model.BuyerProfile;
import com.example.backend.user.model.SellerProfile;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.BuyerProfileRepository;
import com.example.backend.user.repository.SellerProfileRepository;
import com.example.backend.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CommonValidationAndGetService {

    private final UserRepository userRepository;
    private final BuyerProfileRepository buyerProfileRepository;
    private final SellerProfileRepository sellerProfileRepository;
    private final ItemRepository itemRepository;

    public User validateAndGetUser(String userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId " + userId));
    }

    public BuyerProfile validateAndGetBuyerProfile(String userId) throws ResourceNotFoundException {
        return buyerProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer profile not found with userId " + userId));
    }

    public SellerProfile validateAndGetSellerProfile(String userId) throws ResourceNotFoundException {
        return sellerProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller profile not found with userId " + userId));
    }

    public Item validateAndGetItem(String itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + itemId));
    }
}
