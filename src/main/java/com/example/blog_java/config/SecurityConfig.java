package com.example.blog_java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()  // Allow public access to static resources
                        .requestMatchers("/", "/login", "/register","/test-user").permitAll() // Allow access to home and login
                        .requestMatchers(
                                "/css/**", "/js/**", "/images/**", "/dist/**", "/fonts/**", "/icons-reference/**", "/plugins/**", "/vendor/**", "img/**",
                                "/", "/login", "/register","/contact","/contact/**", "/uploads/**"
                        ).permitAll()
                        .requestMatchers("/anonymous_test","/test-user").permitAll() // Allow access to anonymous test
                        .requestMatchers("/user_test").authenticated() // Require authentication for user test
                        .requestMatchers("/admin_test").hasRole("ADMIN") // Require admin role for admin test
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .formLogin(form -> form
                        .permitAll() // Allow everyone to access the default login page
                )
                .logout(logout -> logout.permitAll()); // Allow logout for all

        return http.build();
    }


}
