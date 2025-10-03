package com.example.taskmanagerapi.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "task_generator", sequenceName = "task_seq", allocationSize = 1)
  private Long id;

  @NotBlank(message = "Task title is required")
  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TaskStatus status;

  private LocalDate dueDate;

  // Many tasks belong to one project
  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;
}
