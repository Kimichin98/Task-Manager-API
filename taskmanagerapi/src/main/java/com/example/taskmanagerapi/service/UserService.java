package com.example.taskmanagerapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("User not found"));
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
