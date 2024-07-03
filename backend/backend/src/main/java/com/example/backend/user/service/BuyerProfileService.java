package com.example.backend.user.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.user.dto.BuyerProfileDTO;
import com.example.backend.user.dto.BuyerProfileResponseDTO;
import com.example.backend.user.dto.UserDTO;
import com.example.backend.user.mapper.BuyerProfileMapper;
import com.example.backend.user.model.BuyerProfile;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.BuyerProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyerProfileService {

    private final BuyerProfileRepository buyerProfileRepository;
    private final BuyerProfileMapper buyerProfileMapper;
    private final CommonValidationAndGetService commonValidationAndGetService;

    // Overloaded method for creating buyer profile during sign up process
    public BuyerProfile createBuyerProfile(UserDTO userDTO, String userId) {
        if (userDTO.getRoles().contains(User.Role.ROLE_BUYER.name())) {
            BuyerProfile buyerProfile = new BuyerProfile();
            buyerProfile.setUserId(userId);
            buyerProfile.setShippingAddress(userDTO.getShippingAddress());
            buyerProfile.setBillingAddress(userDTO.getBillingAddress());
            return buyerProfileRepository.save(buyerProfile);
        }
        return null;
    }

    // Overloaded method for creating buyer profile out of sign up process
    public BuyerProfileResponseDTO createBuyerProfile(BuyerProfileDTO buyerProfileDTO, String userId) {
        BuyerProfile buyerProfile = buyerProfileMapper.fromBuyerProfileDTOtoBuyerProfile(buyerProfileDTO);
        buyerProfile.setUserId(userId);
        BuyerProfile savedBuyerProfile = buyerProfileRepository.save(buyerProfile);

        return buyerProfileMapper.fromBuyerProfiletoBuyerProfileResponseDTO(savedBuyerProfile);
    }

    public BuyerProfileResponseDTO getBuyerProfile(String userId) {
        return buyerProfileMapper.fromBuyerProfiletoBuyerProfileResponseDTO(commonValidationAndGetService.validateAndGetBuyerProfileByUserId(userId));
    }

    public BuyerProfileResponseDTO updateBuyerProfile(String userId, BuyerProfileDTO buyerProfileDTO) {
        BuyerProfile existingBuyerProfile = commonValidationAndGetService.validateAndGetBuyerProfile(userId);
        existingBuyerProfile.setBillingAddress(buyerProfileDTO.getBillingAddress());
        existingBuyerProfile.setShippingAddress(buyerProfileDTO.getShippingAddress());

        BuyerProfile updatedBuyerProfile = buyerProfileRepository.save(existingBuyerProfile);
        return buyerProfileMapper.fromBuyerProfiletoBuyerProfileResponseDTO(updatedBuyerProfile);
    }

    public void deleteBuyerProfile(String userId) {
        BuyerProfile existingBuyerProfile = commonValidationAndGetService.validateAndGetBuyerProfileByUserId(userId);
        buyerProfileRepository.delete(existingBuyerProfile);
    }
}
