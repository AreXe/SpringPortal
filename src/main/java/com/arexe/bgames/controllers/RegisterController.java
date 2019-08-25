package com.arexe.bgames.controllers;

import com.arexe.bgames.entity.User;
import com.arexe.bgames.service.UserService;
import com.arexe.bgames.validators.RegisterValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@Slf4j(topic = "bgames.logger")
public class RegisterController {

    private final UserService userService;
    private final MessageSource messageSource;

    @Autowired
    public RegisterController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    //Convert input strings - removes leading and trailing whitespaces
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmer);
    }

    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(value = "/adduser")
    public String registerPost(Model model, @Valid User user, BindingResult result, Locale locale) {

        String returnPage;

        User existingEmail = userService.findUserByEmail(user.getEmail());
        User existingLogin = userService.findUserByLogin(user.getLogin());

        new RegisterValidator().validateEmailExist(existingEmail, result);
        new RegisterValidator().validateLoginExist(existingLogin, result);
        new RegisterValidator().validate(user, result);

        if (result.hasErrors()) {
            log.error("[REG] Register failed: " + result.toString());
            returnPage = "register";
        } else {
            userService.saveUser(user);
            log.info("[REG] New account registered: " + user.getLogin() + ", email: "+ user.getEmail());
            model.addAttribute("message", messageSource.getMessage("register.success", null, locale));
            returnPage = "login";
        }

        return returnPage;
    }
}
