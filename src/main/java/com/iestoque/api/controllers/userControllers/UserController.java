package com.iestoque.api.controllers.userControllers;


import com.iestoque.api.domain.user.*;
import com.iestoque.api.domain.user.userServices.DeleteUserBy;
import com.iestoque.api.domain.user.userServices.DisableUserService;
import com.iestoque.api.domain.user.userServices.EnableUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
@AllArgsConstructor
public class UserController {

    private final DisableUserService disableUserService;
    private final EnableUserService enableUserService;

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

    @PostMapping("/disable")
    public ResponseEntity<String> desabeUserByLogin(@RequestBody UserDisableEnableDTO userDisableEnableDTO) {
        disableUserService.disableUser(userDisableEnableDTO.login());
        return ResponseEntity.ok("User disabled sucessfully");

    }

    @PostMapping("/enable")
    public ResponseEntity<String> enableUserByLogin(@RequestBody UserDisableEnableDTO userDisableEnableDTO) {
    enableUserService.enableUser(userDisableEnableDTO.login());
    return ResponseEntity.ok("User enabled sucessfully");
    }

}
