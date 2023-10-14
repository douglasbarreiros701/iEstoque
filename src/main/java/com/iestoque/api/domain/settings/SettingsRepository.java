package com.iestoque.api.domain.settings;

import com.iestoque.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
    List<Settings> findSettingsByUser(User user);
}
