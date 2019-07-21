package com.arexe.bgames.service;

import com.arexe.bgames.entity.User;
import com.arexe.bgames.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = adminRepository.findAll();
        return userList;
    }

    @Override
    public List<User> findUsersByName(String name) {
        List<User> userList = adminRepository.findUsersByName(name);
        return userList;
    }

    @Override
    public User getUserById(int id) {
        User userById = adminRepository.findUserById(id);
        return userById;
    }

    @Override
    public void updateUserStatus(int id, int roleNumber, int active) {
        adminRepository.updateUserActivation(id, active);
        adminRepository.updateUserRole(id, roleNumber);
    }

    @Override
    public void deleteUserById(int id) {
        adminRepository.deleteUserByIdFromUserRoleTable(id);
        adminRepository.deleteUserByIdFromUserTable(id);
    }
}
