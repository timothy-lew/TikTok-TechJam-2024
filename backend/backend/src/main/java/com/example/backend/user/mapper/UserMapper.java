package com.example.backend.user.mapper;

import com.example.backend.user.dto.UserDTO;
import com.example.backend.user.dto.UserResponseDTO;
import com.example.backend.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", source = "roles", qualifiedByName = "rolesToStringSet")
    @Mapping(target = "cashBalance", source = "wallet.cashBalance")
    @Mapping(target = "coinBalance", source = "wallet.coinBalance")
    UserResponseDTO toUserResponseDTO(User user);

//    @Mapping(target = "roles", source = "roles", qualifiedByName = "stringSetToRoles")
//    @Mapping(target = "wallet.cashBalance", source = "cashBalance")
//    @Mapping(target = "wallet.coinBalance", source = "coinBalance")
//    User toUser(UserDTO userDTO);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "stringSetToRoles")
    @Mapping(target = "wallet", ignore = true)
        // Ignore wallet mapping for user creation
    User toUserForCreate(UserDTO userDTO);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "stringSetToRoles")
    @Mapping(target = "wallet.cashBalance", source = "cashBalance")
    @Mapping(target = "wallet.coinBalance", source = "coinBalance")
    User toUserForUpdate(UserDTO userDTO);

    @Named("rolesToStringSet")
    default Set<String> rolesToStringSet(Set<User.Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Named("stringSetToRoles")
    default Set<User.Role> stringSetToRoles(Set<String> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(User.Role::valueOf)
                .collect(Collectors.toSet());
    }
}