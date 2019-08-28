package com.arexe.bgames.service;

import com.arexe.bgames.entity.Role;
import com.arexe.bgames.entity.User;
import com.arexe.bgames.repository.RoleRepository;
import com.arexe.bgames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        user.setActive(0); //0 = disabled, new account registered
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByRole("ROLE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(role)));

        userRepository.save(user);

    }

    @Override
    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public void updateUserPassword(String newPassword, String email) {
        userRepository.updateUserPassword(passwordEncoder.encode(newPassword), email);
    }

    @Override
    public void updateUserProfile(String login, String firstName, String lastName, String email) {
        userRepository.updateUserProfile(login, firstName, lastName, email);
    }

    @Override
    public void updateActiveStatus(int id, int active, String activationToken) {
        userRepository.updateActiveStatus(id, active, activationToken);
    }
}
