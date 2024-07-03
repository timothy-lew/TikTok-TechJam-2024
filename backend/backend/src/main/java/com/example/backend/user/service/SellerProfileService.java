package com.example.backend.user.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.user.dto.SellerProfileDTO;
import com.example.backend.user.dto.SellerProfileResponseDTO;
import com.example.backend.user.dto.UserDTO;
import com.example.backend.user.mapper.SellerProfileMapper;
import com.example.backend.user.model.SellerProfile;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.SellerProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SellerProfileService {

    private final SellerProfileRepository sellerProfileRepository;
    private final SellerProfileMapper sellerProfileMapper;
    private final CommonValidationAndGetService commonValidationAndGetService;

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
    public SellerProfileResponseDTO createSellerProfile(SellerProfileDTO sellerProfileDTO, String userId) {
        SellerProfile sellerProfile = sellerProfileMapper.fromSellerProfileDTOtoSellerProfile(sellerProfileDTO);
        sellerProfile.setUserId(userId);
        SellerProfile savedSellerProfile = sellerProfileRepository.save(sellerProfile);

        return sellerProfileMapper.fromSellerProfiletoSellerProfileResponseDTO(savedSellerProfile);
    }

    public SellerProfileResponseDTO getSellerProfile(String userId) {
        return sellerProfileMapper.fromSellerProfiletoSellerProfileResponseDTO(
                commonValidationAndGetService.validateAndGetSellerProfileByUserId(userId));
    }

    public SellerProfileResponseDTO updateSellerProfile(String userId, SellerProfileDTO sellerProfileDTO) {
        SellerProfile existingSellerProfile = commonValidationAndGetService.validateAndGetSellerProfileByUserId(userId);
        existingSellerProfile.setBusinessName(sellerProfileDTO.getBusinessName());
        existingSellerProfile.setBusinessDescription(sellerProfileDTO.getBusinessDescription());

        SellerProfile updatedSellerProfile = sellerProfileRepository.save(existingSellerProfile);
        return sellerProfileMapper.fromSellerProfiletoSellerProfileResponseDTO(updatedSellerProfile);
    }

    public void deleteSellerProfile(String userId) {
        SellerProfile existingSellerProfile = commonValidationAndGetService.validateAndGetSellerProfileByUserId(userId);
        sellerProfileRepository.delete(existingSellerProfile);
    }

}
