package com.example.backend.user.service;

import com.example.backend.user.model.BuyerProfile;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.BuyerProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyerProfileService {
    private final BuyerProfileRepository buyerProfileRepository;

    public BuyerProfileService(BuyerProfileRepository buyerProfileRepository) {
        this.buyerProfileRepository = buyerProfileRepository;
    }

    public void createBuyerProfileForUser(User user) {
        BuyerProfile buyerProfile = new BuyerProfile();
        buyerProfile.setUser(user);
        buyerProfileRepository.save(buyerProfile);
    }
}
