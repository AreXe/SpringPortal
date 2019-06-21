package com.arexe.portal.service;

import com.arexe.portal.entity.User;
import com.arexe.portal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserServiceTest(UserService userService, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @BeforeEach
    void init() {
        userRepository.deleteAll();
    }

    @Test
    void userListShouldBeEmptyOnStart() {
        //when
        List<User> userList = userRepository.findAll();
        //then
        assertTrue(userList.isEmpty());
    }

    @Test
    void addingNewUserReturnsTheSameUser() {
        //given
        User user = getTestUser();
        //when
        userService.saveUser(user);
        List<User> all = userRepository.findAll();
        User addedUser = all.get(0);
        //then
        assertEquals(user.getLogin(), addedUser.getLogin());
        assertEquals(user.getEmail(), addedUser.getEmail());
        assertEquals(user.getPassword(), addedUser.getPassword());
    }

    @Test
    void shouldReturnProperUserByFindingByLogin() {
        //given
        User user = getTestUser();
        userService.saveUser(user);
        //when
        User testUser = userService.findUserByLogin("test");
        //then
        assertEquals(user.getLogin(), testUser.getLogin());
        assertEquals(user.getEmail(), testUser.getEmail());
        assertEquals(user.getPassword(), testUser.getPassword());
    }

    @Test
    void shouldReturnProperUserByFindingByEmail() {
        //given
        User user = getTestUser();
        userService.saveUser(user);
        //when
        User testUser = userService.findUserByEmail("test@example.com");
        //then
        assertEquals(user.getLogin(), testUser.getLogin());
        assertEquals(user.getEmail(), testUser.getEmail());
        assertEquals(user.getPassword(), testUser.getPassword());
    }

    @Test
    void updatingUserProfileReturnsUpdatedFields() {
        //given
        User user = getTestUser();
        userService.saveUser(user);
        String newLogin = "test2";
        String newFirstName = "Test2";
        String newLastName = "Account2";
        //when
        userService.updateUserProfile(newLogin, newFirstName, newLastName, user.getEmail());
        User testUser = userService.findUserByEmail(user.getEmail());
        //then
        assertEquals(newLogin, testUser.getLogin());
        assertEquals(newFirstName, testUser.getFirstName());
        assertEquals(newLastName, testUser.getLastName());
    }

    @Test
    void updatingUserPasswordReturnsUpdatedPassword() {
        //given
        User user = getTestUser();
        userService.saveUser(user);
        String newPassword = "pass123";
        //when
        userService.updateUserPassword(newPassword, user.getEmail());
        User testUser = userService.findUserByEmail(user.getEmail());
        //then
        assertTrue(passwordEncoder.matches(newPassword, testUser.getPassword()));
    }

    private User getTestUser() {
        User user = new User();
        user.setLogin("test");
        user.setEmail("test@example.com");
        user.setPassword("test123");
        user.setFirstName("Test");
        user.setLastName("Account");
        return user;
    }

}
