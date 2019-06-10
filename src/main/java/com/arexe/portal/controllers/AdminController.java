package com.arexe.portal.controllers;

import com.arexe.portal.entity.User;
import com.arexe.portal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/admin")
    @Secured(value = {"ROLE_ADMIN"})
    public String adminMainPage() {
        return "admin/admin";
    }

    @GetMapping(value = "/admin/users")
    @Secured(value = {"ROLE_ADMIN"})
    public String userPanel(Model model) {
        List<User> userList = getUserList();
        model.addAttribute("userList", userList);
        return "admin/users";
    }

    @GetMapping(value = "/admin/users/search/{name}")
    @Secured(value = {"ROLE_ADMIN"})
    public String searchUsers(Model model, @PathVariable("name") String name) {
        List<User> userList = findUsersByName(name);
        model.addAttribute("userList", userList);
        return "admin/users";
    }

    @GetMapping(value = "/admin/users/edit/{id}")
    @Secured(value = {"ROLE_ADMIN"})
    public String userEditPanel(Model model, @PathVariable("id") int id) {
        User userById = getUserById(id);
        model.addAttribute("user", userById);

        Map<Integer, String> roleMap = roleMap();
        Map<Integer, String> activityMap = activityMap();
        model.addAttribute("roleMap", roleMap);
        model.addAttribute("activityMap", activityMap);

        return "admin/edituser";
    }

    @PostMapping(value = "/admin/updateuser/{id}")
    @Secured(value = {"ROLE_ADMIN"})
    public String updateUserFromPanel(User user, @PathVariable("id") int id) {
        adminService.updateUserStatus(id, user.getRoleNumber(), user.getActive());
        return "redirect:/admin/users";
    }

    @DeleteMapping(value = "/admin/deleteuser/{id}")
    @Secured(value = {"ROLE_ADMIN"})
    public String deleteUserAction(@PathVariable("id") int id) {
        adminService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    private List<User> getUserList() {
        List<User> userList = adminService.getUserList();
        for (User user : userList) {
            int roleNumber = user.getRoles().iterator().next().getId();
            user.setRoleNumber(roleNumber);
        }
        return userList;
    }

    private List<User> findUsersByName(String name) {
        List<User> userList = adminService.findUsersByName(name);
        for (User user : userList) {
            int roleNumber = user.getRoles().iterator().next().getId();
            user.setRoleNumber(roleNumber);
        }
        return userList;
    }

    private User getUserById(int id) {
        User userById = adminService.getUserById(id);
        int roleNumber = userById.getRoles().iterator().next().getId();
        userById.setRoleNumber(roleNumber);
        return userById;
    }

    private Map<Integer, String> activityMap() {
        Map<Integer, String> activityMap = new HashMap<>();
        activityMap.put(0, "Disabled");
        activityMap.put(1, "Active");
        return activityMap;
    }

    private Map<Integer, String> roleMap() {
        Map<Integer, String> roleMap = new HashMap<>();
        roleMap.put(1, "Admin");
        roleMap.put(2, "User");
        return roleMap;
    }
}
