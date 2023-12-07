package com.iestoque.api.domain.user.userServices;

import com.iestoque.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service


public class DisableUserService {


    private final UserRepository userRepository;

    @Autowired
    public DisableUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public void disableUser(String login){
        userRepository.disableUser(login);
    }


}
