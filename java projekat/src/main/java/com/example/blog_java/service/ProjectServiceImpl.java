package com.example.blog_java.service;

import com.example.blog_java.model.Project;
import com.example.blog_java.repository.ProjectRepository;
import com.example.blog_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> getProjectById(String id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project addMember(String projectId, String memberId) {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isPresent()) {
            Project project = projectOpt.get();
            if (!project.getMemberIds().contains(memberId)) {
                project.getMemberIds().add(memberId);
                return projectRepository.save(project);
            }
        }
        throw new IllegalArgumentException("Project not found or member already exists");
    }

    @Override
    public Project removeMember(String projectId, String memberId) {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isPresent()) {
            Project project = projectOpt.get();
            project.getMemberIds().remove(memberId);
            return projectRepository.save(project);
        }
        throw new IllegalArgumentException("Project or member not found");
    }

    public List<Project> findByManagerId(String managerId) {
        return projectRepository.findByManagerId(managerId);
    }

    public Optional<Project> findById(String id) {
        return projectRepository.findById(id); // Pronalazi projekat po ID-u
    }

    public void deleteById(String id) {
        projectRepository.deleteById(id); // Briše projekat po ID-u
    }
}
