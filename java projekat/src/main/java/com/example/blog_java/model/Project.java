package com.example.blog_java.model;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "projects")
public class Project {
    @Id
    private String id;

    private String name; // Naziv projekta
    private String description; // Opis projekta
    private String managerId; // ID korisnika koji je menadžer

    @ElementCollection
    private List<String> memberIds = new ArrayList<>(); // Lista članova (ID-ovi korisnika)

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
