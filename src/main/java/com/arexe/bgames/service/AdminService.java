package com.arexe.bgames.service;

import com.arexe.bgames.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

    List<User> getUserList();
    Page<User> getUserListPageable(Pageable pageable);
    List<User> findUsersByName(String name);
    User getUserById(int id);
    void updateUserStatus(int id, int roleNumber, int active);
    void deleteUserById(int id);
}
