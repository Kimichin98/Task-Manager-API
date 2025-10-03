package com.example.taskmanagerapi.model;

import java.util.List;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "projects")
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "project_generator", sequenceName = "project_seq", allocationSize = 1)
  private Long id;

  @NotBlank(message = "Project name is required")
  private String name;

  private String description;

  // Many projects belong to one user
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  // One project has many tasks
  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Task> tasks;
}
