package com.iestoque.api.domain.settings;

import com.iestoque.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Settings")
@Table(name = "user_settings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean dark_mode;
    private Boolean notification_email;
    private Boolean notification_browser;
    private Boolean notification_news;

    @OneToOne
    @MapsId
    @JoinColumn(name = "myuser_id")
    private User user;

    public Settings(SettingsDTO data) {
        this.dark_mode = data.dark_mode();
        this.notification_email = data.notification_email();
        this.notification_browser = data.notification_browser();
        this.notification_news = data.notification_news();
    }


}

