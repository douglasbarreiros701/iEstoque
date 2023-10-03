package com.iestoque.api.controllers.productsControllers;


import com.iestoque.api.domain.product.ProductGetDTO;
import com.iestoque.api.domain.product.ProductsRepository;
import com.iestoque.api.domain.product.ProductsJPA;
import com.iestoque.api.domain.user.User;
import com.iestoque.api.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsRepository productsInterface;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @PostMapping("/{id}")
    public ProductsJPA productRegister(@RequestBody ProductsJPA product, @PathVariable Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Usuário não encontrado com o ID" + id));
       product.setUser(user);
        return  productsInterface.save(product);
    }

    @GetMapping
    public List<ProductGetDTO> listarProduto(){
        return productsInterface.findAll().stream().map(ProductGetDTO::new).toList();

    }
}
