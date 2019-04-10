package com.arexe.portal.service;

import com.arexe.portal.entity.User;
import com.arexe.portal.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<User> getUserList() {
        List<User> userList = adminRepository.findAll();
        return userList;
    }
}
