package com.iestoque.api.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductsJPA, Long> {
}
