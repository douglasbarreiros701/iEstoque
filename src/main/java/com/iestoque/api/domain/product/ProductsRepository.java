package com.iestoque.api.domain.product;

import com.iestoque.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductsJPA, Long> {
    List<ProductsJPA> findProductByUser(User user);

}
