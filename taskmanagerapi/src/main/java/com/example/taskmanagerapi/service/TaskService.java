package com.example.taskmanagerapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taskmanagerapi.model.Project;
import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.model.TaskStatus;
import com.example.taskmanagerapi.repository.ProjectRepository;
import com.example.taskmanagerapi.repository.TaskRepository;


import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
  private final TaskRepository taskRepository;
  private final ProjectRepository projectRepository;

  public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
    this.taskRepository = taskRepository;
    this.projectRepository = projectRepository;
  }

  public Task createTask(Long projectId, Task task) {
    Project project = projectRepository.findById(projectId)
        .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    task.setProject(project);
    return taskRepository.save(task);
  }

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task getTaskById(Long id) {
    return taskRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Task not found"));
  }

  public Task updateTask(Long id, Task updatedTask) {
    Task task = getTaskById(id);
    task.setTitle(updatedTask.getTitle());
    task.setDescription(updatedTask.getDescription());
    task.setStatus(updatedTask.getStatus());
    task.setDueDate(updatedTask.getDueDate());
    return taskRepository.save(task);
  }

  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }

  public List<Task> getTasksByStatus(TaskStatus status) {
    return taskRepository.findByStatus(status);
  }

  public List<Task> getOverdueTasks() {
    return taskRepository.findByDueDateBefore(LocalDate.now());
  }
}
