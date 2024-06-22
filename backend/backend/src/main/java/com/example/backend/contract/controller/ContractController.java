package com.example.backend.contract.controller;

import com.example.backend.contract.dto.SendCryptoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.backend.contract.service.ContractService;
import com.example.backend.user.dto.UserDTO;
import com.example.backend.user.dto.UserResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ContractController {
    private final ContractService contractService;

    // this endpoint is an entry point for local dev
    // alex's endpoint will call contractService.sendCrypto(sendCryptoDTO)
     @PostMapping("/transfer")
     public ResponseEntity<BigDecimal> sendCrypto(@RequestBody @Valid SendCryptoDTO
     sendCryptoDTO) {
         log.info("body = {}", sendCryptoDTO);
         BigDecimal balance = contractService.sendCrypto(sendCryptoDTO);
     return new ResponseEntity<>(balance, HttpStatus.CREATED);
     }
}
