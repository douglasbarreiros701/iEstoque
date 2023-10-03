package com.iestoque.api.domain.user;

public record UserGetDTO (Long id, String login, String email){
    public UserGetDTO(User data) {
        this(data.getId(), data.getEmail(), data.getLogin());
    }
}
