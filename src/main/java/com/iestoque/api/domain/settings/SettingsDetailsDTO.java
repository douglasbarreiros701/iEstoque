package com.iestoque.api.domain.settings;

public record SettingsDetailsDTO(
        Boolean dark_mode,
        Boolean notification_email,
        Boolean notification_browser,
        Boolean notification_news
) {

//    public ConfigurationDetailsDTO(User user){
//        this(user.getUserSettings().getDark_mode(), user.getUserSettings().getNotification_email(), user.getUserSettings().getNotification_browser(), user.getUserSettings().getNotification_news());
//    }

}

