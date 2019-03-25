package com.arexe.portal.controllers;

import com.arexe.portal.entity.User;
import com.arexe.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.POST;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GET
    @RequestMapping(value = "/register")
    public String registerForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @POST
    @RequestMapping(value = "/adduser")
    public String registerPost(Model model, User user){

        userService.saveUser(user);

        return "index";
    }

}
