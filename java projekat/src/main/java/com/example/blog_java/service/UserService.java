package com.example.blog_java.service;

import com.example.blog_java.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);               // To save a user
    Optional<User> getUserById(String id);    // To get user by ID
    List<User> getAllUsers();               // To get all users
    void deleteUser(String id);               // To delete user by ID
    Optional<User> getUserByEmail(String email); // To get user by email
}
