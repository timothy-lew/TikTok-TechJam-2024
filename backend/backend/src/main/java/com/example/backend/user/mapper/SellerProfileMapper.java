package com.example.backend.user.mapper;

import com.example.backend.user.dto.SellerProfileDTO;
import com.example.backend.user.dto.SellerProfileResponseDTO;
import com.example.backend.user.model.SellerProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerProfileMapper {
    SellerProfileResponseDTO fromSellerProfiletoSellerProfileResponseDTO(SellerProfile sellerProfile);

    SellerProfile fromSellerProfileDTOtoSellerProfile(SellerProfileDTO sellerProfileDTO);
}
