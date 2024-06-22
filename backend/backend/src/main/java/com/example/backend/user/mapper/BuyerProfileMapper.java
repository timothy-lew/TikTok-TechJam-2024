package com.example.backend.user.mapper;

import com.example.backend.user.dto.BuyerProfileDTO;
import com.example.backend.user.dto.BuyerProfileResponseDTO;
import com.example.backend.user.model.BuyerProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuyerProfileMapper {

    BuyerProfileResponseDTO fromBuyerProfiletoBuyerProfileResponseDTO(BuyerProfile buyerProfile);

    BuyerProfile fromBuyerProfileDTOtoBuyerProfile(BuyerProfileDTO buyerProfileDTO);
}
