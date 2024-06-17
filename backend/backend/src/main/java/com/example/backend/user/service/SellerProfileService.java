package com.example.backend.user.service;

import com.example.backend.user.model.SellerProfile;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.SellerProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerProfileService {
    private final SellerProfileRepository sellerProfileRepository;

    public SellerProfileService(SellerProfileRepository sellerProfileRepository) {
        this.sellerProfileRepository = sellerProfileRepository;
    }

    public void createSellerProfileForUser(User user) {
        SellerProfile sellerProfile = new SellerProfile();
        sellerProfile.setUser(user);
        sellerProfileRepository.save(sellerProfile);
    }
}
