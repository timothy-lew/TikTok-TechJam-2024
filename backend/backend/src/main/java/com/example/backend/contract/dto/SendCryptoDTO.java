package com.example.backend.contract.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SendCryptoDTO {
    private String address;
    private Long amount;
}
