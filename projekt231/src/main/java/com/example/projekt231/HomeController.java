package com.example.projekt231;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    public HomeController() {
        System.out.println("home");
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
}
