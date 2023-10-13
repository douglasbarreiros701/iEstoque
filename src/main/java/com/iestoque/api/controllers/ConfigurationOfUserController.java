package com.iestoque.api.controllers;

import com.iestoque.api.domain.configurations.*;
import com.iestoque.api.domain.user.User;
import com.iestoque.api.domain.user.UserRepository;
import com.iestoque.api.domain.user.userServices.GetUserIdByName;
import com.iestoque.api.domain.user.userServices.UserServices;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configurations")
public class ConfigurationOfUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    private UserServices userServices;

    @PutMapping("/{userName}")
    public ResponseEntity<User> changeSettings(@RequestBody Configurations configurations, ConfigurationUpdateDTO configurationUpdateDTO,  @PathVariable String userName, Authentication authentication, User user){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUser = userDetails.getUsername();

        if (!authenticatedUser.equals(userName)){
            throw new AccessDeniedException("You don't have acess at this configurations");
        }

//        GetUserIdByName getUserIdByName = new GetUserIdByName();
//        var userId = getUserIdByName.getUserIdByName(userName);

        User settingUpdated = userServices.uPdateSettings(authenticatedUser, configurationUpdateDTO);

        return ResponseEntity.ok(settingUpdated);
    }


}
