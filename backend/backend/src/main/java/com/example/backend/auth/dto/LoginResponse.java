package com.example.backend.auth.dto;

import com.example.backend.user.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private UserResponseDTO user;
}
