package com.arexe.bgames.repository;

import com.arexe.bgames.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(int id);
    User findUserByEmail(String email);
    User findUserByLogin(String login);

    @Modifying
    @Query("UPDATE User u SET u.password=:newPassword WHERE u.email=:email")
    void updateUserPassword(@Param("newPassword") String newPassword, @Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.login=:login, u.firstName=:firstName, u.lastName=:lastName WHERE u.email=:email")
    void updateUserProfile(@Param("login") String login, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.active=:active WHERE u.activationToken=:activationToken AND u.id=:id")
    void updateActiveStatus(@Param("id") int id, @Param("active") int active, @Param("activationToken") String activationToken);
}
