package com.iestoque.api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {



    @Modifying
    @Query("UPDATE user u SET u.isActive = false WHERE u.login = :userLogin")
    void disableUser(@Param("userLogin") String userLogin);

    @Modifying
    @Query("UPDATE user u SET u.isActive = true WHERE u.login = :userLogin") //java persistence query language (JPQL)
    void enableUser(@Param("userLogin") String userLogin);

    User findByLogin(String login);
    User findByEmail(String email);

}
