package com.example.backend.user.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.user.dto.UserDTO;
import com.example.backend.user.dto.UserResponseDTO;
import com.example.backend.user.mapper.UserMapper;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.UserRepository;
import com.example.backend.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final WalletService walletService;
    private final BuyerProfileService buyerProfileService;
    private final SellerProfileService sellerProfileService;
    private final CommonValidationAndGetService commonValidationAndGetService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper,
                       WalletService walletService, BuyerProfileService buyerProfileService, SellerProfileService sellerProfileService, CommonValidationAndGetService commonValidationAndGetService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.walletService = walletService;
        this.buyerProfileService = buyerProfileService;
        this.sellerProfileService = sellerProfileService;
        this.commonValidationAndGetService = commonValidationAndGetService;
    }

    @Transactional
    public UserResponseDTO createUser(UserDTO userDTO) {
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

    @Transactional
    public UserResponseDTO updateUser(String userId, UserDTO userDTO) {
        User existingUser = commonValidationAndGetService.validateAndGetUser(userId);
        User updatedUser = userMapper.fromUserDTOtoUserForUpdate(userDTO);
        updatedUser.setId(existingUser.getId());
        updatedUser.setPassword(existingUser.getPassword()); // Avoid updating password here
        updatedUser.setWallet(existingUser.getWallet()); // Retain existing wallet
        updatedUser.setBuyerProfile(existingUser.getBuyerProfile()); // Retain existing buyer profile
        updatedUser.setSellerProfile(existingUser.getSellerProfile()); // Retain existing seller profile

        User savedUser = userRepository.save(updatedUser);
        return userMapper.fromUsertoUserResponseDTO(savedUser);
    }

    // TODO: Cascade of deletions by calling respective delete methods in BuyerProfileService, SellerProfileService, WalletService
    // TODO: SellerProfileService subsequently need to delete all items listed by the seller
    @Transactional
    public void deleteUser(String userId) {
        userRepository.delete(commonValidationAndGetService.validateAndGetUser(userId));

//        User deletingUser = commonValidationAndGetService.validateAndGetUser(userId);
//        buyerProfileService.deleteBuyerProfile(deletingUser);
//        sellerProfileService.deleteSellerProfile(deletingUser);
//        walletService.deleteWallet(deletingUser);
    }
}
