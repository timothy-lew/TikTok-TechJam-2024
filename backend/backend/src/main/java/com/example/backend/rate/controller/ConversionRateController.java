package com.example.backend.rate.controller;

import com.example.backend.rate.model.ConversionRate;
import com.example.backend.rate.service.ConversionRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conversionRate")
public class ConversionRateController {

    private final ConversionRateService conversionRateService;

    @Autowired
    public ConversionRateController(ConversionRateService conversionRateService) {
        this.conversionRateService = conversionRateService;
    }

    @PostMapping("/set")
    public ResponseEntity<ConversionRate> setConversionRate(@RequestParam float rate) {
        return ResponseEntity.ok(conversionRateService.setRate(rate));
    }

    @GetMapping("/current")
    public ResponseEntity<ConversionRate> getCurrentConversionRate() {
        return ResponseEntity.ok(conversionRateService.getCurrentRate());
    }
}
