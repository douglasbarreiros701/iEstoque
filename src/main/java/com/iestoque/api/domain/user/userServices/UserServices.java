package com.iestoque.api.domain.user.userServices;

import com.iestoque.api.domain.configurations.ConfigurationDetailsDTO;
import com.iestoque.api.domain.configurations.ConfigurationUpdateDTO;
import com.iestoque.api.domain.user.User;

public interface UserServices {
    User uPdateSettings(String userName, ConfigurationUpdateDTO configurationUpdateDTO);
}
