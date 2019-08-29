package com.arexe.bgames.controllers;

import com.arexe.bgames.entity.PasswordToken;
import com.arexe.bgames.entity.User;
import com.arexe.bgames.service.UserService;
import com.arexe.bgames.utils.EmailSender;
import com.arexe.bgames.validators.ChangePasswordValidator;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
    @Value("${passwordReset.link}")
    private String passwordResetLink;

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
            if (user.getActive() == 0 && user.getActivationToken().equals(activationToken)) {
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

    @GetMapping(value = "/password-reset")
    public String passwordReset(Model model) {
        PasswordToken passwordToken = new PasswordToken();
        model.addAttribute("passwordToken", passwordToken);
        return "password-reset";
    }

    @PostMapping(value = "/password-reset")
    public String sendPasswordReset(Model model, PasswordToken passwordToken, Locale locale) {
        String returnPage;
        if (Optional.ofNullable(userService.findUserByEmail(passwordToken.getEmail())).isPresent()) {
            String passwordResetToken = UUID.randomUUID().toString();
            passwordToken.setPasswordToken(passwordResetToken);
            passwordToken.setExpirationDate(LocalDateTime.now().plusMinutes(15));
            userService.savePasswordToken(passwordToken);
            User user = userService.findUserByEmail(passwordToken.getEmail());

            //Email section for password reset
            final Context thContext = new Context(locale);
            thContext.setVariable("user", user);
            thContext.setVariable("passwordResetLink", passwordResetLink + user.getId() + "/" + passwordToken.getPasswordToken());
            String content = templateEngine.process("email-pr", thContext);
            emailSender.sendMessage(passwordToken.getEmail(), messageSource.getMessage("email.passwordReset.title", null, locale), content);

            model.addAttribute("message", messageSource.getMessage("register.passwordReset.process", null, locale));
            returnPage = "login";
        } else {
            model.addAttribute("message", messageSource.getMessage("register.passwordReset.invalidMail", null, locale));
            returnPage = "password-reset";
        }
        return returnPage;
    }

    @GetMapping(value = "/password-reset/{id}/{passwordToken}")
    public String passwordResetForm(Model model, @PathVariable("id") int id, @PathVariable("passwordToken") String passwordToken, Locale locale, RedirectAttributes redirectAttributes) {
        if (Optional.ofNullable(userService.findUserById(id)).isPresent() && Optional.ofNullable(userService.findPasswordToken(passwordToken)).isPresent()) {
            User user = userService.findUserById(id);
            PasswordToken token = userService.findPasswordToken(passwordToken);
            if (user.getEmail().equals(token.getEmail()) && LocalDateTime.now().isBefore(token.getExpirationDate())) {
                model.addAttribute("user", user);
                return "password-reset-form";
            } else if (!user.getEmail().equals(token.getEmail())) {
                return "login";
            } else if (user.getEmail().equals(token.getEmail()) && LocalDateTime.now().isAfter(token.getExpirationDate())) {
                model.addAttribute("message", messageSource.getMessage("register.passwordReset.timeExpired", null, locale));
                return "login";
            } else {
                return "login";
            }
        }
        return "login";
    }

    @PostMapping(value = "/password-reset-proceed")
    public String changePasswordFromResetForm(Model model, User user, BindingResult result, Locale locale) {
        String redirectPage;
        new ChangePasswordValidator().checkPassword(user.getNewPassword(), result);
        if (result.hasErrors()) {
            redirectPage = "password-reset-form";
        } else {
            userService.updateUserPassword(user.getNewPassword(), user.getEmail());
            redirectPage = "login";
            model.addAttribute("message", messageSource.getMessage("changePassword.success", null, locale));
        }
        return redirectPage;
    }

}
