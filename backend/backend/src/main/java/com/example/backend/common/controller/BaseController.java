package com.example.backend.common.controller;

import com.example.backend.auth.model.UserPrincipal;
import com.example.backend.common.exception.InvalidPrincipalException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {
    public String getLoginUserId(UserPrincipal principal) throws InvalidPrincipalException {
        if (principal == null) {
            throw new InvalidPrincipalException("Empty or unknown login credentials.");
        }
        return principal.getId();
    }

    // Method to check if user is ROLE_SELLER, returns true if user is not a seller
    public boolean userNotSeller(UserPrincipal principal) {
        return principal.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_SELLER"));
    }

    // Method to check if user is ROLE_BUYER, returns true if user is not a buyer
    public boolean userNotBuyer(UserPrincipal principal) {
        return principal.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_BUYER"));
    }
}
