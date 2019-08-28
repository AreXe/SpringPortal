package com.arexe.bgames.controllers;

import com.arexe.bgames.entity.User;
import com.arexe.bgames.service.UserService;
import com.arexe.bgames.utils.EmailSender;
import com.arexe.bgames.validators.RegisterValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Controller
@Slf4j(topic = "bgames.logger")
public class RegisterController {

    private final UserService userService;
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    private final MessageSource messageSource;

    @Value("${activation.link}")
    private String activationLink;

    @Autowired
    public RegisterController(UserService userService, EmailSender emailSender, TemplateEngine templateEngine, MessageSource messageSource) {
        this.userService = userService;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
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
            String activationToken = UUID.randomUUID().toString();
            user.setActivationToken(activationToken);
            userService.saveUser(user);
            log.info("[REG] New account registered: " + user.getLogin() + ", email: " + user.getEmail());

            //Email section for account activation
            final Context thContext = new Context(locale);
            thContext.setVariable("user", user);
            thContext.setVariable("actLink", activationLink + user.getId() + "/" + user.getActivationToken());
            String content = templateEngine.process("email-act", thContext);
            emailSender.sendMessage(user.getEmail(), messageSource.getMessage("email.activation.title", null, locale), content);

            model.addAttribute("message", messageSource.getMessage("register.success", null, locale));
            returnPage = "login";
        }

        return returnPage;
    }

    @GetMapping(value = "/activate/{id}/{activationToken}")
    public String accountActivation(Model model, @PathVariable("id") int id, @PathVariable("activationToken") String activationToken, Locale locale) {
        if (Optional.ofNullable(userService.findUserById(id)).isPresent()) {
            User user = userService.findUserById(id);
            if (user.getActivationToken().equals(activationToken)) {
                userService.updateActiveStatus(id, 1, activationToken);
                model.addAttribute("message", messageSource.getMessage("register.activated", null, locale));
                return "login";
            } else {
                model.addAttribute("message", messageSource.getMessage("register.activation.failed", null, locale));
                return "login";
            }
        }
        return "login";
    }

}
