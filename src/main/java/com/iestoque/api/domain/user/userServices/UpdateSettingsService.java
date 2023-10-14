package com.iestoque.api.domain.user.userServices;

import com.iestoque.api.domain.settings.SettingsRepository;
import com.iestoque.api.domain.settings.SettingsDTO;
import com.iestoque.api.domain.user.User;
import com.iestoque.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateSettingsService implements UserServices{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingsRepository settingsRepository;

    public User uPdateSettings(String authenticatedUser, SettingsDTO data){
        User user = userRepository.findByLogin(authenticatedUser);

        if (user == null){
            throw new UsernameNotFoundException("The user does not exist or was not found");
        }

        user.getUserSettings().setDark_mode(data.dark_mode());
        user.getUserSettings().setNotification_news(data.notification_news());
        user.getUserSettings().setNotification_email(data.notification_email());
        user.getUserSettings().setNotification_browser(data.notification_browser());

        return userRepository.save(user);



    }



}
