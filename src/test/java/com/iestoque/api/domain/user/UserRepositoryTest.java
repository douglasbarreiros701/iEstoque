package com.iestoque.api.domain.user;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;


    private User user;

    @Test
    @DisplayName("Must return  'returns false when the user is not saved correctly' ")
    void SaveUserScenario1() {

        user = registerUser("Teste", "12345678", "testerepository@teste.com");
        var savedCorrectly = isUserSavedCorrectly(user,"Teste","testerepository@teste.com" ,"12345678");

        assertTrue(savedCorrectly, "The user was not saved");
    }

    @Test
    @DisplayName("Must return  'the user is null when the fields are not filled in' ")
    void SaveUserScenario2() {

        user = registerUser(null, null, null);
        var isNull = isUserNull(user,null, null, null);

        assertFalse(isNull, "The user is null");
    }





    private User registerUser(String login, String password, String email) {
        var user = new User(dadosUsuario(login, password, email));
        entityManager.persist(user);
        entityManager.flush();
        return user;
    }


    private boolean isUserSavedCorrectly(User user, String login, String email, String password) {
        User savedUser = entityManager.find(User.class, user.getId());

        if (savedUser == null) {
            return false;
        }
        if (!login.equals(savedUser.getLogin()) || !email.equals(savedUser.getEmail()) || !password.equals(savedUser.getPassword())) {
            return false;
        }
        return true;

    }

    private boolean isUserNull(User user, String login, String email, String password) {
        User savedUser = entityManager.find(User.class, user.getId());

        if (savedUser.getLogin() == null || savedUser.getEmail() == null || savedUser.getPassword() == null) {
            return false;
        }
        return true;

    }

    private UserRegisterDTO dadosUsuario(String login, String password, String email) {
        return new UserRegisterDTO(
               login, password, email

        );
    }
}