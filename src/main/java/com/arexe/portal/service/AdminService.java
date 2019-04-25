package com.arexe.portal.service;

import com.arexe.portal.entity.User;

import java.util.List;

public interface AdminService {

    List<User> getUserList();
    List<User> findUsersByName(String name);
    User getUserById(int id);
    void updateUserStatus(int id, int roleNumber, int active);
    void deleteUserById(int id);
}
