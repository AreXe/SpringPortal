package com.arexe.portal.controllers;

import com.arexe.portal.entity.User;
import com.arexe.portal.service.UserService;
import com.arexe.portal.validators.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(value = "/adduser")
    public String registerPost(Model model, User user, BindingResult result) {

        String returnPage = null;

        User existingEmail = userService.findUserByEmail(user.getEmail());
        User existingLogin = userService.findUserByLogin(user.getLogin());

        new RegisterValidator().validateEmailExist(existingEmail, result);
        new RegisterValidator().validateLoginExist(existingLogin, result);
        new RegisterValidator().validate(user, result);

        if (result.hasErrors()) {
            returnPage = "register";
        } else {
            userService.saveUser(user);
            returnPage = "index";
        }

        return returnPage;
    }
}
