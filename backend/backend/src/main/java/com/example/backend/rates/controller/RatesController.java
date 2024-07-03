package com.example.backend.rates.controller;

import com.example.backend.common.exception.ResourceNotFoundException;
import com.example.backend.rates.dto.DiscountRateDTO;
import com.example.backend.rates.service.RatesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rates/")
@RequiredArgsConstructor
@Slf4j
public class RatesController {

    private final RatesService ratesService;

    @PostMapping("/conversion")
    public ResponseEntity<?> setConversionRate(@RequestParam float rate) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ratesService.setConversionRate(rate));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error setting conversion rate", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/conversion/current")
    public ResponseEntity<?> getCurrentConversionRate() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ratesService.getCurrentConversionRate());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching current conversion rate", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/conversion")
    public ResponseEntity<?> getAllConversionRates() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ratesService.getAllConversionRates());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching all conversion rates", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/discount")
    public ResponseEntity<?> setDiscountRate(@RequestBody @Valid DiscountRateDTO discountRateDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ratesService.setDiscountRate(discountRateDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error setting discount rate", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/discount")
    public ResponseEntity<?> getDiscountRate(@RequestParam(required = false) String sellerProfileId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ratesService.getDiscountRate(sellerProfileId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching discount rate", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/discount/all")
    public ResponseEntity<?> getAllDiscountRates() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ratesService.getAllDiscountRates());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching all discount rates", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");

        }
    }

    @DeleteMapping("/discount")
    public ResponseEntity<?> removeDiscount(@RequestParam(required = false) String sellerProfileId) {
        try {
            ratesService.removeDiscount(sellerProfileId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Item deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error deleting discount rate", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}
