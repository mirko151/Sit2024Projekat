package com.example.blog_java.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    // Route for anonymous access
    @GetMapping("/anonymous_test")
    public String anonymousTest() {
        return "anonymous_test"; // Points to a template for this route
    }

    // Route for logged-in users only
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user_test")
    public String userTest() {
        return "user_test"; // Points to a template for this route
    }

    // Route for admins only
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin_test")
    public String adminTest(Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            System.out.println("Logged in user: " + username);

            for (GrantedAuthority authority : authentication.getAuthorities()) {
                System.out.println("User role: " + authority.getAuthority());
            }
        }else {
            System.out.println("authentication in user is null");
        }

        return "admin_test"; // Points to a template for this route
    }
}
