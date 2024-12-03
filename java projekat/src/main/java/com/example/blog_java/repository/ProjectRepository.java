package com.example.blog_java.repository;

import com.example.blog_java.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.blog_java.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    // Mogu se dodati prilagođene metode ako su potrebne
    List<Project> findByManagerId(String managerId);

}
