package com.example.backend.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private String name;
    private Set<String> roles;
    private Float cashBalance;
    private Float coinBalance;
}