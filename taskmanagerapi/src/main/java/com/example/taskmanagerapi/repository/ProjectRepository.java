package com.example.taskmanagerapi.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanagerapi.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
  List<Project> findByUserId(Long userId);
}
