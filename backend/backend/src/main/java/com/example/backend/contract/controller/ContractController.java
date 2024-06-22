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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ContractController {
    private final ContractService contractService;

     @PostMapping("/transfer")
     public ResponseEntity<SendCryptoDTO> sendCrypto(@RequestBody @Valid SendCryptoDTO
     sendCryptoDTO) {
         log.info("body = {}", sendCryptoDTO);
         SendCryptoDTO responseDTO = contractService.sendCrypto(sendCryptoDTO);
     return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
     }
}
