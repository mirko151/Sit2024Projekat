package com.example.blog_java.repository;

import com.example.blog_java.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // You can add custom query methods here if needed
    User findByEmail(String email); // Example custom query to find a user by email
}
