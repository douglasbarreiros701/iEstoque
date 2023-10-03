package com.iestoque.api.controllers;

import com.iestoque.api.domain.product.ProductsJPA;
import com.iestoque.api.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {



    @Autowired
    UserRepository repository;

    @GetMapping
    public List<UserGetDTO> listar(){
        return repository.findAll().stream().map(UserGetDTO::new).toList();
    }



}
