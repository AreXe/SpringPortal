package com.arexe.portal.admin;

import com.arexe.portal.entity.User;
import com.arexe.portal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GET
    @RequestMapping(value = "/admin")
    @Secured(value = {"ROLE_ADMIN"})
    public String adminMainPage() {
        return "admin/admin";
    }

    @GET
    @RequestMapping(value = "/admin/users")
    @Secured(value = {"ROLE_ADMIN"})
    public String userPanel(Model model) {
        List<User> userList = getUserList();
        model.addAttribute("userList", userList);
        return "admin/users";
    }

    private List<User> getUserList() {
        List<User> userList = adminService.getUserList();
        for (User user : userList) {
            int roleNumber = user.getRoles().iterator().next().getId();
            user.setRoleNumber(roleNumber);
        }
        return userList;
    }
}
