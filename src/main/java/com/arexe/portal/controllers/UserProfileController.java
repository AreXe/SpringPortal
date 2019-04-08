package com.arexe.portal.controllers;

import com.arexe.portal.entity.User;
import com.arexe.portal.service.UserService;
import com.arexe.portal.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GET
    @RequestMapping(value = "/profile")
    public String showUserProfile(Model model) {

        String loggedUser = UserUtils.getLoggedUser();
        User user = userService.findUserByEmail(loggedUser);

        int roleNumber = user.getRoles().iterator().next().getId();
        user.setRoleNumber(roleNumber);

        model.addAttribute("user", user);

        return "profile";
    }
}
