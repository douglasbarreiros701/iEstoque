package com.iestoque.api.domain.settings;

public record SettingsDTO(
        Long id,
        Boolean dark_mode,
        Boolean notification_email,
        Boolean notification_browser,
        Boolean notification_news
) {
}
