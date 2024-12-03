package com.example.blog_java.controller;

import com.example.blog_java.model.Project;
import com.example.blog_java.service.ProjectService;
import com.example.blog_java.service.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.blog_java.model.Project;
import com.example.blog_java.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @GetMapping("/")
    public String getProjects(Model model, Principal principal) {
        String managerId = principal.getName(); // ID trenutno ulogovanog menadžera
        List<Project> projects = projectService.findByManagerId(managerId); // Pronađi projekte za ovog menadžera
        model.addAttribute("projects", projects); // Prosledi projekte u view
        return "projects"; // Naziv HTML fajla (projects.html)
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable String id, Principal principal) {
        String managerId = principal.getName();
        // Proveri da li projekat pripada trenutnom korisniku
        Optional<Project> project = projectService.findById(id);

        System.out.println(project);

        if (project.isPresent() && project.get().getManagerId().equals(managerId)) {
            projectService.deleteById(id); // Obriši projekat
        }
        return "redirect:/projects"; // Vrati korisnika na listu projekata
    }

    @GetMapping("/create")
    public String showCreateProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "create-project"; // Vraća stranicu za kreiranje projekta
    }

    @PostMapping("/create")
    public String createProject(@RequestParam String name,
                                @RequestParam String description,
                                Principal principal) {
        // Pretpostavlja se da `principal.getName()` vraća ID ili email trenutno ulogovanog korisnika
        String managerId = principal.getName();

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setManagerId(managerId);

        projectService.createProject(project); // Snimanje projekta u bazu

        return "redirect:/projects"; // Redirekcija na stranicu sa listom projekata
    }

    @PostMapping("/{projectId}/add-member")
    public String addMember(@PathVariable String projectId, @RequestParam String memberId) {
        projectService.addMember(projectId, memberId);
        return "redirect:/projects/" + projectId; // Preusmerava na detalje projekta
    }

    @PostMapping("/{projectId}/remove-member")
    public String removeMember(@PathVariable String projectId, @RequestParam String memberId) {
        projectService.removeMember(projectId, memberId);
        return "redirect:/projects/" + projectId; // Preusmerava na detalje projekta
    }
}
