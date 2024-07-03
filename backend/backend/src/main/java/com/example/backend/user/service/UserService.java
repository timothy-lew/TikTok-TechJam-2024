package com.example.backend.user.service;

import com.example.backend.common.exception.InvalidPrincipalException;
import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.user.dto.UserDTO;
import com.example.backend.user.dto.UserResponseDTO;
import com.example.backend.user.dto.UserUpdateDTO;
import com.example.backend.user.mapper.UserMapper;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.UserRepository;
import com.example.backend.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final WalletService walletService;
    private final BuyerProfileService buyerProfileService;
    private final SellerProfileService sellerProfileService;
    private final CommonValidationAndGetService commonValidationAndGetService;

    public UserResponseDTO createUser(UserDTO userDTO) {
        // Check if username already exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new InvalidPrincipalException("Username already exists");
        }

        User user = userMapper.fromUserDTOtoUserForCreate(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);

        savedUser.setWallet(walletService.createWallet(savedUser.getId(), userDTO.getWalletAddress()));
        savedUser.setBuyerProfile(buyerProfileService.createBuyerProfile(userDTO, savedUser.getId()));
        savedUser.setSellerProfile(sellerProfileService.createSellerProfile(userDTO, savedUser.getId()));

        return userMapper.fromUsertoUserResponseDTO(userRepository.save(savedUser));
    }

    // Overloaded method for normal use case
    public UserResponseDTO getUserById(String userId) {
        return userMapper.fromUsertoUserResponseDTO(commonValidationAndGetService.validateAndGetUser(userId));
    }

    // Overloaded method for JwtAuthenticationFilter to obtain User as return type instead of UserResponseDTO
    public User getUserById(String userId, Boolean isNormalUseCase) {
        return commonValidationAndGetService.validateAndGetUser(userId);
    }

    public UserResponseDTO updateUser(String userId, UserUpdateDTO userDTO) {
        User existingUser = commonValidationAndGetService.validateAndGetUser(userId);
        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());

        return userMapper.fromUsertoUserResponseDTO(userRepository.save(existingUser));
    }

    public void deleteUser(String userId) {
        User deletingUser = commonValidationAndGetService.validateAndGetUser(userId);
        if (deletingUser.getRoles().contains(User.Role.ROLE_SELLER)) {
            sellerProfileService.deleteSellerProfile(userId);
        }
        if (deletingUser.getRoles().contains(User.Role.ROLE_BUYER)) {
            buyerProfileService.deleteBuyerProfile(userId);
        }
        walletService.deleteWallet(userId);
        userRepository.delete(commonValidationAndGetService.validateAndGetUser(userId));
    }
}
