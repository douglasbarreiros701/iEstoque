package com.iestoque.api.domain.user;

import com.iestoque.api.domain.product.ProductsJPA;
import com.iestoque.api.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record UserRegisterDTO(
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$\n")
        String email
) {
}
