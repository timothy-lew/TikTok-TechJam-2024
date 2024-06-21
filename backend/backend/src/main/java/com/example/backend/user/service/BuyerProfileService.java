package com.example.backend.user.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.user.dto.BuyerProfileDTO;
import com.example.backend.user.dto.BuyerProfileResponseDTO;
import com.example.backend.user.dto.UserDTO;
import com.example.backend.user.mapper.BuyerProfileMapper;
import com.example.backend.user.model.BuyerProfile;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.BuyerProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyerProfileService {

    private final BuyerProfileRepository buyerProfileRepository;
    private final BuyerProfileMapper buyerProfileMapper;
    private final CommonValidationAndGetService commonValidationAndGetService;

    public BuyerProfileService(BuyerProfileRepository buyerProfileRepository, BuyerProfileMapper buyerProfileMapper, CommonValidationAndGetService commonValidationAndGetService) {
        this.buyerProfileRepository = buyerProfileRepository;
        this.buyerProfileMapper = buyerProfileMapper;
        this.commonValidationAndGetService = commonValidationAndGetService;
    }

    // TODO Incorporate creation of transaction history in createBuyerProfile methods

    // Overloaded method for creating buyer profile during sign up process
    public BuyerProfile createBuyerProfile(UserDTO userDTO, String userId) {
        if (userDTO.getRoles().contains(User.Role.ROLE_BUYER.name())) {
            BuyerProfile buyerProfile = new BuyerProfile();
            buyerProfile.setUserId(userId);
            buyerProfile.setShippingAddress(userDTO.getShippingAddress());
            buyerProfile.setBillingAddress(userDTO.getBillingAddress());
            buyerProfile.setDefaultPaymentMethod(userDTO.getDefaultPaymentMethod());
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
        return buyerProfileMapper.fromBuyerProfiletoBuyerProfileResponseDTO(commonValidationAndGetService.validateAndGetBuyerProfile(userId));
    }

//    public BuyerProfileResponseDTO updateBuyerProfile(String userId, BuyerProfileDTO buyerProfileDTO) {
//        BuyerProfile existingBuyerProfile = commonValidationAndGetService.validateAndGetBuyerProfile(userId);
//        BuyerProfile updatedBuyerProfile =
//
//        BuyerProfile buyerProfile = buyerProfileRepository.findByUserId(userId)
//                .orElseThrow(() -> new NotFoundException("Buyer profile not found"));
//        buyerProfileMapper.updateBuyerProfile(buyerProfileDTO, buyerProfile);
//        BuyerProfile updatedBuyerProfile = buyerProfileRepository.save(buyerProfile);
//        return buyerProfileMapper.fromBuyerProfiletoBuyerProfileResponseDTO(updatedBuyerProfile);
//    }
}
