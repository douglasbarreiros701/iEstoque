package com.iestoque.api.domain.user.userServices;

import com.iestoque.api.domain.user.UserDisableEnableDTO;
import com.iestoque.api.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnableUserService {

    private final UserRepository userRepository;


    public EnableUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void enableUser(String login){
        userRepository.enableUser(login);
    }
}
