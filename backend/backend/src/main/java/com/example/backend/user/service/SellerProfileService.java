package com.example.backend.user.service;

import com.example.backend.user.dto.UserDTO;
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

    // Overloaded method for creating buyer profile during sign up process
    public SellerProfile createSellerProfile(UserDTO userDTO, String userId) {
        if (userDTO.getRoles().contains(User.Role.ROLE_SELLER.name())) {
            SellerProfile sellerProfile = new SellerProfile();
            sellerProfile.setUserId(userId);
            sellerProfile.setBusinessName(userDTO.getBusinessName());
            sellerProfile.setBusinessDescription(userDTO.getBusinessDescription());
            return sellerProfileRepository.save(sellerProfile);
        }
        return null;
    }

    // Overloaded method for creating seller profile out of sign up process
//    public SellerProfile createSellerProfile(SellerProfile sellerProfile, User user) {
//        sellerProfile.setUser(user);
//        return sellerProfileRepository.save(sellerProfile);
//    }

}
