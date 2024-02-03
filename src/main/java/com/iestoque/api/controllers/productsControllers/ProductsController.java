package com.iestoque.api.controllers.productsControllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.iestoque.api.domain.product.ProductGetDTO;
import com.iestoque.api.domain.product.ProductsRepository;
import com.iestoque.api.domain.product.ProductsJPA;
import com.iestoque.api.domain.user.User;
import com.iestoque.api.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "bearer-key")
public class ProductsController {

    private static final Logger log = LoggerFactory.getLogger(ProductsController.class);

    @Autowired
    ProductsRepository productsInterface;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @DeleteMapping("/{userName}/{productName}")
    public ResponseEntity<Object> delete(@PathVariable String userName, @PathVariable String productName, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUser = userDetails.getUsername();

        if(!authenticatedUser.equals(userName)){
            throw new AccessDeniedException("You don't have acess at this products");
        }

        User user = userRepository.findByLogin(userName);
        ProductsJPA existingProduct = productsInterface.findByProductnameAndUser(productName, user);

        if (existingProduct == null) {
            throw new AccessDeniedException("You don't have access to this product");
        }

        log.debug("Deleting product: {}", existingProduct);

        productsInterface.delete(existingProduct);

        log.debug("Product deleted successfully.");
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("/{userName}")
    public ProductsJPA productRegister(@RequestBody ProductsJPA product, @PathVariable String userName, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUser = userDetails.getUsername();

        if (!authenticatedUser.equals(userName)){
            throw new AccessDeniedException("You don't have acess at this products");
        }

        User user = userRepository.findByLogin(userName);

        product.setUser(user);
        return productsInterface.save(product);
    }

    @GetMapping("/{userName}")
    public List<ProductGetDTO> listarProduto(Authentication authentication, @PathVariable String userName){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();

        if (!authenticatedUsername.equals(userName)){
            throw new AccessDeniedException("You don't have acess at this products");
        }

        User user = userRepository.findByLogin(userName);


        List<ProductsJPA> userProducts = productsInterface.findProductByUser(user);

        List<ProductGetDTO> productGetResponse = userProducts.stream().map(
                product -> new ProductGetDTO(
                        product.getProductname(), product.getBrand(), product.getLocation(),
                        product.getBatch(), product.getDue_date(), product.getFactory_date(), product.getProduct_type())
        ).toList();

        return productGetResponse;

    }

}
