package com.arexe.portal.validators;

import com.arexe.portal.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ChangePasswordValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        @SuppressWarnings("unused")
        User user = (User) o;
    }

    public void checkPassword(String newPassword, Errors errors) {
        boolean isPasswordCorrect = PatternChecker.check(ValidPatterns.PASSWORD, newPassword);
        if (!isPasswordCorrect) {
            errors.rejectValue("newPassword", "error.userPasswordIsNotMatch");
        }
    }
}
