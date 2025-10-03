package com.example.taskmanagerapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
  private Long id;

  @NotBlank(message = "Name is required")
  private String name;

  @Email(message = "Invalid email")
  @Column(unique = true, nullable = false)
  private String email;

  // One user can own multiple projects
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Project> projects;
}
