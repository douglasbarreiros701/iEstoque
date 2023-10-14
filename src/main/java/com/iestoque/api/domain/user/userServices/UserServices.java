package com.iestoque.api.domain.user.userServices;

import com.iestoque.api.domain.settings.SettingsDTO;
import com.iestoque.api.domain.user.User;

public interface UserServices {
    User uPdateSettings(String authenticatedUser, SettingsDTO data);
}
