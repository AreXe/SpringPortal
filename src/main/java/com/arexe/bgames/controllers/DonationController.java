package com.arexe.bgames.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonationController {

    @GetMapping(value = "/donate")
    public String showDonationPage() {
        return "donate";
    }

}