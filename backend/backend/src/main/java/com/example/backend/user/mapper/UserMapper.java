package com.example.backend.user.mapper;

import com.example.backend.user.dto.UserDTO;
import com.example.backend.user.dto.UserResponseDTO;
import com.example.backend.user.model.User;
import com.example.backend.wallet.mapper.WalletMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {BuyerProfileMapper.class, SellerProfileMapper.class, WalletMapper.class})
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "rolesToStringSet")
//    @Mapping(target = "buyerProfileId", source = "buyerProfile.id")
//    @Mapping(target = "shippingAddress", source = "buyerProfile.shippingAddress")
//    @Mapping(target = "billingAddress", source = "buyerProfile.billingAddress")
//    @Mapping(target = "defaultPaymentMethod", source = "buyerProfile.defaultPaymentMethod")
//    @Mapping(target = "sellerProfileId", source = "sellerProfile.id")
//    @Mapping(target = "businessName", source = "sellerProfile.businessName")
//    @Mapping(target = "businessDescription", source = "sellerProfile.businessDescription")
//    @Mapping(target = "walletId", source = "wallet.id")
//    @Mapping(target = "cashBalance", source = "wallet.cashBalance")
//    @Mapping(target = "coinBalance", source = "wallet.coinBalance")
    UserResponseDTO fromUsertoUserResponseDTO(User user);

    // Buyer and seller profile fields are populated only during user creation, hence not ignored here.
    @Mapping(target = "roles", source = "roles", qualifiedByName = "stringSetToRoles")
    @Mapping(target = "wallet", ignore = true)
    User fromUserDTOtoUserForCreate(UserDTO userDTO);

    // Buyer and seller profile fields are not populated during user update, but in separate endpoints, hence ignored here.
    @Mapping(target = "roles", source = "roles", qualifiedByName = "stringSetToRoles")
    @Mapping(target = "buyerProfile", ignore = true)
    @Mapping(target = "sellerProfile", ignore = true)
    @Mapping(target = "wallet", ignore = true)
    User fromUserDTOtoUserForUpdate(UserDTO userDTO);

    @Named("rolesToStringSet")
    default Set<String> rolesToStringSet(Set<User.Role> roles) {
        if (roles == null) {
            return Collections.emptySet();
        }
        return roles.stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Named("stringSetToRoles")
    default Set<User.Role> stringSetToRoles(Set<String> roles) {
        if (roles == null) {
            return Collections.emptySet();
        }
        return roles.stream()
                .map(User.Role::valueOf)
                .collect(Collectors.toSet());
    }
}