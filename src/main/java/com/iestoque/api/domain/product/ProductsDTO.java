package com.iestoque.api.domain.product;

public record ProductsDTO(
        String productname,
        String brand,
        String location,
        String batch,
        String due_date,
        String factory_date,
        String product_type

) {
}
