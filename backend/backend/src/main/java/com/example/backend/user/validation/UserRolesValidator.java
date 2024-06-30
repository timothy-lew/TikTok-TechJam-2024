package com.example.backend.user.validation;

import com.example.backend.user.dto.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class UserRolesValidator implements ConstraintValidator<ValidProfileFields, UserDTO> {

    @Override
    public void initialize(ValidProfileFields constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
        Set<String> roles = userDTO.getRoles();
        boolean isBuyer = roles.contains("ROLE_BUYER");
        boolean isSeller = roles.contains("ROLE_SELLER");

        boolean isBuyerValid = isBuyer &&
                userDTO.getShippingAddress() != null && !userDTO.getShippingAddress().isEmpty() &&
                userDTO.getBillingAddress() != null && !userDTO.getBillingAddress().isEmpty();

        boolean isSellerValid = isSeller &&
                userDTO.getBusinessName() != null && !userDTO.getBusinessName().isEmpty() &&
                userDTO.getBusinessDescription() != null && !userDTO.getBusinessDescription().isEmpty();

        if (isBuyer && !isBuyerValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Shipping address and billing address are required for buyers")
                    .addPropertyNode("shippingAddress")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate("Shipping address and billing address are required for buyers")
                    .addPropertyNode("billingAddress")
                    .addConstraintViolation();
            return false;
        }

        if (isSeller && !isSellerValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Business name and business description are required for sellers")
                    .addPropertyNode("businessName")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate("Business name and business description are required for sellers")
                    .addPropertyNode("businessDescription")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
