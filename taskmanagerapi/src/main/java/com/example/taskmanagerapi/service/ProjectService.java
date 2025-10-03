package com.example.taskmanagerapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taskmanagerapi.model.Project;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.repository.ProjectRepository;
import com.example.taskmanagerapi.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService {
  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;

  public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
    this.projectRepository = projectRepository;
    this.userRepository = userRepository;
  }

  public Project createProject(Long userId, Project project) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found"));
    project.setUser(user);
    return projectRepository.save(project);
  }

  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

  public Project getProjectById(Long id) {
    return projectRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Project not found"));
  }

  public void deleteProject(Long id) {
    projectRepository.deleteById(id);
  }
}
