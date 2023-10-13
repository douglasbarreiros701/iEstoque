package com.iestoque.api.domain.configurations;

public record ConfigurationDetailsDTO(
        Boolean dark_mode,
        Boolean notification_email,
        Boolean notification_browser,
        Boolean notification_news
) {

    public ConfigurationDetailsDTO(Configurations configurations){
        this(configurations.getDark_mode(), configurations.getNotification_email(), configurations.getNotification_browser(), configurations.getNotification_news());
    }

}

