package com.example.libraryStore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// MyController - It has method that respond to client request
public class WelcomeController {
    @Value("${spring.application.name}")
    String theNameAppHave;

    // The @GetMapping maps the / URL pattern to the Home() method
    @GetMapping("/home")
    public String Home(Model myModel){
        myModel.addAttribute("theNameAppHave", theNameAppHave);
        return "home";
    }
}
