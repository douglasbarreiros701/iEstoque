package com.iestoque.api.domain.product.ErrorHandling;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message) {
        super(message);
    }


}
