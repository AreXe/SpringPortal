package com.arexe.portal.controllers;

import com.arexe.portal.entity.User;
import com.arexe.portal.service.UserService;
import com.arexe.portal.utils.UserUtils;
import com.arexe.portal.validators.ChangePasswordValidator;
import com.arexe.portal.validators.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;

@Controller
public class UserProfileController {

    private final UserService userService;
    private final MessageSource messageSource;

    @Autowired
    public UserProfileController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping(value = "/profile")
    public String showUserProfile(Model model) {
        User user = getLoggedUser();

        int roleNumber = user.getRoles().iterator().next().getId();
        user.setRoleNumber(roleNumber);

        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping(value = "/changepassword")
    public String changeUserPassword(Model model) {
        User user = getLoggedUser();
        model.addAttribute("user", user);
        return "changepassword";
    }

    @PostMapping(value = "/updatepassword")
    public String updateUserPassword(User user, BindingResult result, Model model, Locale locale) {
        String redirectPage;

        new ChangePasswordValidator().checkPassword(user.getNewPassword(), result);
        if (result.hasErrors()) {
            redirectPage = "changepassword";
        } else {
            userService.updateUserPassword(user.getNewPassword(), user.getEmail());
            redirectPage = "changepassword";
            model.addAttribute("message", messageSource.getMessage("changePassword.success", null, locale));
        }
        return redirectPage;
    }

    @GetMapping(value = "/editprofile")
    public String editUserProfile(Model model) {
        User user = getLoggedUser();
        model.addAttribute("user", user);
        return "editprofile";
    }

    @PostMapping(value = "/updateprofile")
    public String updateUserProfile(User user, BindingResult result, Model model, Locale locale) {
        String redirectPage;
        User actualUser = getLoggedUser();
        User existingLogin = userService.findUserByLogin(user.getLogin());
        if (!actualUser.getLogin().equals(user.getLogin())) {
            new RegisterValidator().validateLoginExist(existingLogin, result);
        }

        if (result.hasErrors()) {
            redirectPage = "editprofile";
        } else {
            userService.updateUserProfile(user.getLogin(), user.getFirstName(), user.getLastName(), user.getEmail());
            redirectPage = "editinfo";
            model.addAttribute("message", messageSource.getMessage("editProfile.success", null, locale));
        }

        return redirectPage;
    }

    private User getLoggedUser(){
        String loggedUser = UserUtils.getLoggedUser();
        return userService.findUserByEmail(loggedUser);
    }
}
