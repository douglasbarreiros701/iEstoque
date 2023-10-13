package com.iestoque.api.domain.user.userServices;

import com.iestoque.api.domain.user.User;
import com.iestoque.api.domain.user.UserRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserIdByName {
    @Autowired
    private UserRepository userRepository;

    public Long getUserIdByName(String username) {
        User user = userRepository.findByLogin(username);

        if (user != null) {
            return user.getId();
        } else {
            throw new OpenApiResourceNotFoundException("User not found");
        }
    }



}
