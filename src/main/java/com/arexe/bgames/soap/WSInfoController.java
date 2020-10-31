package com.arexe.bgames.soap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WSInfoController {

    @GetMapping(value = "/ws-info")
    public String showWSInfoPage() {
        return "ws-info";
    }

}
