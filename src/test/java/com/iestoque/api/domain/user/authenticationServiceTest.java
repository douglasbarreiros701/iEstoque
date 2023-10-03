package com.iestoque.api.domain.user;


import com.iestoque.api.controllers.userControllers.UserAuthenticationController;
import com.iestoque.api.controllers.userControllers.UserController;
import com.iestoque.api.domain.product.ProductsJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)

public class authenticationServiceTest {

    @Autowired
    private UserAuthenticationController userAuthenticationController;
    @Autowired
    private UserController userController;

    private User user;
    private List<ProductsJPA> products = new ArrayList<>();


    @BeforeEach
    public void setUp(){
       user = new User("Fabio", "123456", "teste@email.com", products);
    }


    @Test
    @Transactional
    void UserRegisterTest(){
        UserRegisterDTO userDTO = new UserRegisterDTO(
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getProducts()
        );


        ResponseEntity response = userAuthenticationController.register(userDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        userController.delete("teste@email.com");

    }



}

