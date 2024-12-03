package com.example.blog_java.service;

import com.example.blog_java.enums.UserStatus;
import com.example.blog_java.model.User;
import com.example.blog_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println(user);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
/*
        if (user == null || user.getStatus() != UserStatus.ACTIVE) {
            throw new UsernameNotFoundException("User not found or not active");
        }
*/
        // Create a Spring Security User object
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getType().name()) // Make sure this returns the correct roles
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }
}

