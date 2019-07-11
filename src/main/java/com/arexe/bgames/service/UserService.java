package com.arexe.bgames.service;

import com.arexe.bgames.entity.User;

public interface UserService {

    void saveUser(User user);
    User findUserByEmail(String email);
    User findUserByLogin(String login);
    void updateUserPassword(String newPassword, String email);
    void updateUserProfile(String login, String firstName, String lastName, String email);
}
