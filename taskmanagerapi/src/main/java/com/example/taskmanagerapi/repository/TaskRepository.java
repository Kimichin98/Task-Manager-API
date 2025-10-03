package com.example.taskmanagerapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.model.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByProjectId(Long projectId);
  List<Task> findByStatus(TaskStatus status);
  List<Task> findByDueDateBefore(LocalDate date);
}
