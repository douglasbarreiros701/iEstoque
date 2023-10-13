package com.iestoque.api.domain.configurations;

public record ConfigurationDTO(
        Long id,
        Boolean dark_mode,
        Boolean notification_email,
        Boolean notification_browser,
        Boolean notification_news
) {
}
