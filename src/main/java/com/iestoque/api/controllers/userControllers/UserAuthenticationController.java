package com.iestoque.api.controllers.userControllers;

import com.iestoque.api.domain.settings.SettingsRepository;
import com.iestoque.api.domain.settings.Settings;
import com.iestoque.api.domain.user.UserAuthentication.AutenticationData;
import com.iestoque.api.domain.user.User;
import com.iestoque.api.domain.user.UserRegisterDTO;
import com.iestoque.api.domain.user.UserRepository;
import com.iestoque.api.infra.security.DadosTokenJWT;
import com.iestoque.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private SettingsRepository settingsRepository;


    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody AutenticationData data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication =  authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterDTO data){

        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.login(), encryptedPassword, data.email());

        Settings settings = startConfigurations(newUser);
        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }


    private Settings startConfigurations(User user){
        Settings settings = new Settings();
        settings.setDark_mode(false);
        settings.setNotification_email(true);
        settings.setNotification_browser(true);
        settings.setNotification_news(true);

        settings.setUser(user);
        return settingsRepository.save(settings);
    }
}
