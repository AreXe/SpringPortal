package com.arexe.bgames.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController implements ErrorController {

    @GetMapping(value = "/error")
    public String showErrorPage() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
