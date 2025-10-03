package com.example.taskmanagerapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.model.TaskStatus;
import com.example.taskmanagerapi.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping("/project/{projectId}")
  public Task createTask(@PathVariable Long projectId, @RequestBody Task task) {
    return taskService.createTask(projectId, task);
  }

  @GetMapping
  public List<Task> getAllTasks(@RequestParam(required = false) TaskStatus status) {
    if (status != null)
      return taskService.getTasksByStatus(status);
    return taskService.getAllTasks();
  }

  @GetMapping("/{id}")
  public Task getTask(@PathVariable Long id) {
    return taskService.getTaskById(id);
  }

  @PutMapping("/{id}")
  public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
    return taskService.updateTask(id, task);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
  }

  @GetMapping("/overdue")
  public List<Task> getOverdueTasks() {
    return taskService.getOverdueTasks();
  }
}
