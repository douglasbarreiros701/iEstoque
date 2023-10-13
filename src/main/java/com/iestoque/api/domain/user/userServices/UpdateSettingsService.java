package com.iestoque.api.domain.user.userServices;

import com.iestoque.api.domain.configurations.ConfigurationDetailsDTO;
import com.iestoque.api.domain.configurations.ConfigurationUpdateDTO;
import com.iestoque.api.domain.user.User;
import com.iestoque.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateSettingsService implements UserServices{

    @Autowired
    private UserRepository userRepository;

    public User uPdateSettings(String userName, ConfigurationUpdateDTO configurationUpdateDTO){
        User user = userRepository.findByLogin(userName);

        if (user == null){
            throw new UsernameNotFoundException("The user does not exist or was not found");
        }

        user.getConfigurations().setDark_mode(configurationUpdateDTO.dark_mode());
        user.getConfigurations().setNotification_news(configurationUpdateDTO.notification_news());
        user.getConfigurations().setNotification_email(configurationUpdateDTO.notification_email());
        user.getConfigurations().setNotification_browser(configurationUpdateDTO.notification_browser());

        return userRepository.save(user);
    }

}
