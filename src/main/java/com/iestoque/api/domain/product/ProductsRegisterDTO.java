package com.iestoque.api.domain.product;

import jakarta.validation.constraints.NotBlank;

public record ProductsRegisterDTO(
        @NotBlank
        String productname,
        @NotBlank
        String brand,
        @NotBlank
        String location,
        @NotBlank
        String batch,
        @NotBlank
        String due_date,
        String factory_date,
        String product_type

) {
}
