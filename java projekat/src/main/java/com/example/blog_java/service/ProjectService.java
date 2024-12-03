package com.example.blog_java.service;

import com.example.blog_java.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project createProject(Project project); // Kreiranje projekta
    Optional<Project> getProjectById(String id); // Dohvaćanje projekta po ID-ju
    Project addMember(String projectId, String memberId); // Dodavanje člana
    Project removeMember(String projectId, String memberId); // Uklanjanje člana
    public List<Project> findByManagerId(String managerId);
}
