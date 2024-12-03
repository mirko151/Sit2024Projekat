package com.example.blog_java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Route for home page
    @GetMapping("/")
    public String homePage(Model model) {
        // You can add attributes to the model for dynamic content on the homepage if needed
        return "index"; // Looks for a view template named "index.html"
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // This should point to the login page template
    }
}
