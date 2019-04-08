package com.arexe.portal.controllers;

import com.arexe.portal.entity.User;
import com.arexe.portal.service.UserService;
import com.arexe.portal.utils.UserUtils;
import com.arexe.portal.validators.ChangePasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.Locale;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

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

    @GET
    @RequestMapping(value = "/changepassword")
    public String changeUserPassword(Model model) {
        String loggedUser = UserUtils.getLoggedUser();
        User user = userService.findUserByEmail(loggedUser);
        model.addAttribute("user", user);
        return "changepassword";
    }

    @POST
    @RequestMapping(value = "/updatepassword")
    public String updateUserPassword(User user, BindingResult result, Model model, Locale locale) {
        String redirectPage = null;

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
}
