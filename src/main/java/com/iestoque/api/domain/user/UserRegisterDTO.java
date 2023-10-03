package com.iestoque.api.domain.user;

import com.iestoque.api.domain.product.ProductsJPA;
import com.iestoque.api.domain.user.User;

import java.util.List;

public record UserRegisterDTO(
        String login,
        String password,
        String email,
        List<ProductsJPA> products
) {
}
