package com.iestoque.api.controllers.settingsControllers;

import com.iestoque.api.domain.settings.*;
import com.iestoque.api.domain.user.User;
import com.iestoque.api.domain.user.UserRepository;
import com.iestoque.api.domain.user.userServices.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class SettingsOfUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingsRepository settingsRepository;

    @Autowired
    private UserServices userServices;


    @Transactional
    @PutMapping("/{userName}")
    public ResponseEntity changeSettings(@RequestBody SettingsDTO data, @PathVariable String userName, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUser = userDetails.getUsername();


        if (!authenticatedUser.equals(userName)){
            throw new AccessDeniedException("You don't have acess at this configurations");
        }


        User settingUpdated = userServices.uPdateSettings(authenticatedUser, data);
        SettingsDetailsDTO configurationDetails = new SettingsDetailsDTO(
                settingUpdated.getUserSettings().getDark_mode(),
                settingUpdated.getUserSettings().getNotification_email(),
                settingUpdated.getUserSettings().getNotification_browser(),
                settingUpdated.getUserSettings().getNotification_news()
        );

        return ResponseEntity.ok(configurationDetails);
    }

    @GetMapping("/{userName}")
    public List<SettingsGetDTO> listSettings(@PathVariable String userName, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUser = userDetails.getUsername();

        if (!authenticatedUser.equals(userName)){
            throw new AccessDeniedException("You don't have acess at this configurations");
        }

        User user = userRepository.findByLogin(userName);

        List<Settings> userSettings = settingsRepository.findSettingsByUser(user);

        List<SettingsGetDTO> userSettingsGetResponse = userSettings.stream().map(
                setting -> new SettingsGetDTO(
                        setting.getDark_mode(), setting.getNotification_email(), setting.getNotification_browser(), setting.getNotification_news())
        ).toList();

        return userSettingsGetResponse;

    }


}
