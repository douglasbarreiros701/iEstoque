package com.iestoque.api.domain.configurations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iestoque.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.lang.module.Configuration;

@Entity(name = "configurations")
@Table(name = "config")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Configurations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean dark_mode;
    private Boolean notification_email;
    private Boolean notification_browser;
    private Boolean notification_news;

    @OneToOne
    @JoinColumn(name = "configuracoes_id")
    private User user;

    public Configurations(ConfigurationDTO data) {
        this.dark_mode = data.dark_mode();
        this.notification_email = data.notification_email();
        this.notification_browser = data.notification_browser();
        this.notification_news = data.notification_news();
    }


}

