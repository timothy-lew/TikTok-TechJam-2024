package com.example.backend.rates.controller;

import com.example.backend.rates.dto.DiscountRateDTO;
import com.example.backend.rates.dto.DiscountRateResponseDTO;
import com.example.backend.rates.model.ConversionRate;
import com.example.backend.rates.service.RatesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rates/")
public class RatesController {

    private final RatesService ratesService;

    public RatesController(RatesService ratesService) {
        this.ratesService = ratesService;
    }

    @PostMapping("/conversion")
    public ResponseEntity<ConversionRate> setConversionRate(@RequestParam float rate) {
        return ResponseEntity.ok(ratesService.setConversionRate(rate));
    }

    @GetMapping("/conversion/current")
    public ResponseEntity<ConversionRate> getCurrentConversionRate() {
        return ResponseEntity.ok(ratesService.getCurrentConversionRate());
    }

    @GetMapping("/conversion")
    public ResponseEntity<List<ConversionRate>> getAllConversionRates() {
        return ResponseEntity.ok(ratesService.getAllConversionRates());
    }

    @PostMapping("/discount")
    public ResponseEntity<DiscountRateResponseDTO> setDiscountRate(@RequestBody @Valid DiscountRateDTO discountRateDTO) {
        return ResponseEntity.ok(ratesService.setDiscountRate(discountRateDTO));
    }

    @GetMapping("/discount")
    public ResponseEntity<DiscountRateResponseDTO> getDiscountRate(@RequestParam(required = false) String sellerProfileId) {
        DiscountRateResponseDTO discountRateDTO = ratesService.getDiscountRate(sellerProfileId);
        return discountRateDTO != null ? ResponseEntity.ok(discountRateDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/discount/all")
    public ResponseEntity<List<DiscountRateResponseDTO>> getAllDiscountRates() {
        return ResponseEntity.ok(ratesService.getAllDiscountRates());
    }

    @DeleteMapping("/discount")
    public ResponseEntity<Void> removeDiscount(@RequestParam(required = false) String sellerProfileId) {
        ratesService.removeDiscount(sellerProfileId);
        return ResponseEntity.ok().build();
    }
}
