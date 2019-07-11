package com.arexe.bgames.service;

import com.arexe.bgames.entity.User;
import com.arexe.bgames.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {

    private final AdminService adminService;
    private final AdminRepository adminRepository;

    @Autowired
    AdminServiceTest(AdminService adminService, AdminRepository adminRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }

    @BeforeEach
    void init() {
        adminRepository.deleteAll();
        User user = new User();
        user.setLogin("test");
        user.setEmail("test@example.com");
        user.setPassword("test123");
        user.setFirstName("Test");
        user.setLastName("Account");
        adminRepository.save(user);
    }

    @Test
    void shouldReturnListWithDefaultUser() {
        //when
        List<User> userList = adminService.getUserList();
        //then
        assertFalse(userList.isEmpty());
    }

    @Test
    void findingUsersByNameShouldReturnGivenUser() {
        //given
        String defaultName = "test";
        //when
        List<User> userList = adminService.findUsersByName(defaultName);
        User user = userList.get(0);
        //then
        assertEquals(defaultName, user.getLogin());
    }

    @Test
    void shouldReturnDefaultUserByGivingId() {
        //given
        List<User> userList = adminRepository.findAll();
        User user = userList.get(0);
        String expectedLogin = "test";
        //when
        User userById = adminService.getUserById(user.getId());
        //then
        assertEquals(expectedLogin, userById.getLogin());
    }

    @Test
    void deletingDefaultUserReturnsEmptyList() {
        //given
        List<User> users = adminRepository.findAll();
        User user = users.get(0);
        //when
        adminService.deleteUserById(user.getId());
        List<User> userList = adminService.getUserList();
        //then
        assertTrue(userList.isEmpty());
    }

}
