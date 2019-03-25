package com.arexe.portal.service;

import com.arexe.portal.entity.User;

public interface UserService {

    void saveUser(User user);
    User findUserByEmail(String email);

}
