package com.iestoque.api.domain.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iestoque.api.domain.settings.Settings;
import com.iestoque.api.domain.product.ProductsJPA;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@Table(name = "myuser")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String email;
    private UserRole role;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ProductsJPA> products = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Settings userSettings;


    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(UserRegisterDTO data) {
        this.login = data.login();
        this.password = data.password();
        this.email = data.email();
        this.role = UserRole.USER;
        this.isActive = true;


    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }


    @Override
    public String getUsername() {
        return login;
    }

    public void setUsername(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
