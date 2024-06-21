package com.example.backend.user.controller;

import com.example.backend.auth.model.UserPrincipal;
import com.example.backend.common.controller.BaseController;
import com.example.backend.user.dto.BuyerProfileResponseDTO;
import com.example.backend.user.service.BuyerProfileService;
import com.example.backend.user.service.SellerProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles operations related to buyer and seller profiles, authenticated by the user's role.
 */
@Slf4j
@RestController
@RequestMapping("/api/profiles")
public class ProfileController extends BaseController {

    private final BuyerProfileService buyerProfileService;
    private final SellerProfileService sellerProfileService;

    public ProfileController(BuyerProfileService buyerProfileService, SellerProfileService sellerProfileService) {
        super();
        this.buyerProfileService = buyerProfileService;
        this.sellerProfileService = sellerProfileService;
    }

    @GetMapping("/buyer/{userId}")
    public ResponseEntity<BuyerProfileResponseDTO> getBuyerProfile(@PathVariable String userId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (isUserBuyer(userPrincipal)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        BuyerProfileResponseDTO buyerProfileResponseDTO = buyerProfileService.getBuyerProfile(userId);
        return ResponseEntity.ok(buyerProfileResponseDTO);
    }

//    @PostMapping("/buyer/{userId}")
//    public ResponseEntity<BuyerProfileResponseDTO> updateBuyerProfile(@PathVariable String userId, @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody @Valid BuyerProfileDTO buyerProfileDTO) {
//        if (isUserBuyer(userPrincipal)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//        BuyerProfileResponseDTO updatedBuyerProfileResponseDTO = buyerProfileService.updateBuyerProfile(userId, buyerProfileDTO);
//        return ResponseEntity.ok(updatedBuyerProfileResponseDTO);
//    }
//
//    @GetMapping("/seller/{userId}")
//    public ResponseEntity<SellerProfileDTO> getSellerProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
//        if (!isUserSeller(userPrincipal)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//        String userId = getLoginUserId(userPrincipal);
//        SellerProfileDTO sellerProfileDTO = sellerProfileService.getSellerProfile(userId);
//        return ResponseEntity.ok(sellerProfileDTO);
//    }
//
//    @PostMapping("/seller/{userId}")
//    public ResponseEntity<SellerProfileDTO> updateSellerProfile(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody SellerProfileDTO sellerProfileDTO) {
//        if (!isUserSeller(userPrincipal)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//        String userId = getLoginUserId(userPrincipal);
//        SellerProfileDTO updatedSellerProfileDTO = sellerProfileService.updateSellerProfile(userId, sellerProfileDTO);
//        return ResponseEntity.ok(updatedSellerProfileDTO);
//    }
}

