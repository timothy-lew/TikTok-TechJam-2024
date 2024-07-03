package com.example.backend.user.controller;

import com.example.backend.common.controller.BaseController;
import com.example.backend.common.exception.ResourceNotFoundException;
import com.example.backend.user.dto.BuyerProfileDTO;
import com.example.backend.user.dto.BuyerProfileResponseDTO;
import com.example.backend.user.dto.SellerProfileDTO;
import com.example.backend.user.dto.SellerProfileResponseDTO;
import com.example.backend.user.service.BuyerProfileService;
import com.example.backend.user.service.SellerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles operations related to buyer and seller profiles, authenticated by the user's role.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileController extends BaseController {

    private final BuyerProfileService buyerProfileService;
    private final SellerProfileService sellerProfileService;

    @GetMapping("/buyer/{userId}")
    public ResponseEntity<?> getBuyerProfile(@PathVariable String userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(buyerProfileService.getBuyerProfile(userId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching buyer profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");

        }
    }

    @PutMapping("/buyer/{userId}")
    public ResponseEntity<?> updateBuyerProfile(@PathVariable String userId, @RequestBody @Valid BuyerProfileDTO buyerProfileDTO) {
        try {
            BuyerProfileResponseDTO updatedBuyerProfileResponseDTO = buyerProfileService.updateBuyerProfile(userId, buyerProfileDTO);
            return ResponseEntity.ok(updatedBuyerProfileResponseDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error updating buyer profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/seller/{userId}")
    public ResponseEntity<?> getSellerProfile(@PathVariable String userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(sellerProfileService.getSellerProfile(userId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching seller profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PutMapping("/seller/{userId}")
    public ResponseEntity<?> updateSellerProfile(@PathVariable String userId, @RequestBody @Valid SellerProfileDTO sellerProfileDTO) {
        try {
            SellerProfileResponseDTO updatedSellerProfileResponseDTO = sellerProfileService.updateSellerProfile(userId, sellerProfileDTO);
            return ResponseEntity.ok(updatedSellerProfileResponseDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error updating seller profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
    
}

