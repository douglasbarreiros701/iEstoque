package com.iestoque.api.controllers.userControllers;


import com.iestoque.api.domain.user.*;
import com.iestoque.api.domain.user.userServices.DeleteUserBy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {


    @Autowired
    UserRepository repository;

    @Autowired
    DeleteUserBy deleteUserBy;

    @GetMapping
    public List<UserGetDTO> listar() {
        return repository.findAll().stream().map(UserGetDTO::new).toList();
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Object> delete(@PathVariable String email) {
        if (this.repository.findByEmail(email) != null) return ResponseEntity.badRequest().build();

        deleteUserBy.deleteUserByEmail(email);
        return ResponseEntity.ok().build();


    }

}
