package com.iestoque.api.domain.product;

public record ProductGetDTO(
        String productname,
        String brand,
        String location,
        String batch,
        String due_date,
        String factory_date,
        String product_type
) {

    public ProductGetDTO(ProductsJPA data){
        this(data.getProductname(), data.getBrand(), data.getLocation(), data.getBatch(), data.getDue_date(), data.getFactory_date(), data.getProduct_type() );
    }


}
