package com.example.blog_java.controller;

import com.example.blog_java.enums.UserStatus;
import com.example.blog_java.enums.UserType;
import com.example.blog_java.model.User;
import com.example.blog_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register"; // Points to the registration template
    }

    @GetMapping("/test-user")
    public String testUser(Model model) {
        User selena = new User();

        selena.setEmail("selena@gmail.com");
        selena.setPassword(passwordEncoder.encode("selena"));
        selena.setName("selena");
        selena.setPhone("12323443");
        selena.setType(UserType.ROLE_MANAGER);
        selena.setStatus(UserStatus.ACTIVE);

        userRepository.save(selena);

        User emma = new User();

        emma.setEmail("emma@gmail.com");
        emma.setPassword(passwordEncoder.encode("emma"));
        emma.setName("emma");
        emma.setPhone("7657673443");
        emma.setType(UserType.ROLE_MEMBER);
        emma.setStatus(UserStatus.ACTIVE);

        userRepository.save(emma);

        User john = new User();

        john.setEmail("john@gmail.com");
        john.setPassword(passwordEncoder.encode("john"));
        john.setName("john");
        john.setPhone("717673443");
        john.setType(UserType.ROLE_MEMBER);
        john.setStatus(UserStatus.BANNED);

        userRepository.save(john);

        return "redirect:/login";
    }

    @PostMapping("/register")
    public String registerUser(String email, String password, String phone, String type) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            return "redirect:/register?error"; // Redirect with error
        }

        User newUser = new User();
        newUser.setEmail(email); // Set email
        newUser.setPassword(passwordEncoder.encode(password)); // Encode password

        if(type.equals("member")){
            newUser.setType(UserType.ROLE_MEMBER); // Set default user type
        }else {
            newUser.setType(UserType.ROLE_MANAGER);
        }
        newUser.setPhone(phone);
        newUser.setStatus(UserStatus.ACTIVE);
        userRepository.save(newUser); // Save new user to the database

        return "redirect:/login"; // Redirect to login after registration
    }
}
