package com.iestoque.api.domain.user;

import com.iestoque.api.domain.product.ProductsJPA;

import java.util.ArrayList;
import java.util.List;

public record UserDTO(String login,
                      String email,
                      String password,
                      List<ProductsJPA> products
) {
}
