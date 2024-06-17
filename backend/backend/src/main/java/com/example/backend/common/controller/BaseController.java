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
}
