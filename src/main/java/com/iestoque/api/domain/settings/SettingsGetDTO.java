package com.iestoque.api.domain.settings;

public record SettingsGetDTO(
        Boolean dark_mode,
        Boolean notification_email,
        Boolean notification_browser,
        Boolean notification_news
) {
    public SettingsGetDTO(Settings data){
        this(data.getDark_mode(), data.getNotification_email(), data.getNotification_browser(), data.getNotification_news());
    }

}
