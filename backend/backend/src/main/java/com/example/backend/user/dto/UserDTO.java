package com.example.backend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String id;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Password is required")
    private String password;
    @NotEmpty(message = "At least one role is required")
    private Set<String> roles;
    private Float cashBalance;
    private Float coinBalance;
}