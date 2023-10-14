package com.iestoque.api.domain.user;

import com.iestoque.api.domain.settings.Settings;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRegisterDTO(
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$\n")
        String email,
        @NotNull
        Settings settings
) {
}
