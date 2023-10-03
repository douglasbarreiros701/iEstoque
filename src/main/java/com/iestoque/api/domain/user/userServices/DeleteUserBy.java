package com.iestoque.api.domain.user.userServices;

import com.iestoque.api.domain.user.User;
import com.iestoque.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserBy{


    @Autowired
    private UserRepository userRepository;

    public void deleteUserByEmail(String email){

        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
    }



}
