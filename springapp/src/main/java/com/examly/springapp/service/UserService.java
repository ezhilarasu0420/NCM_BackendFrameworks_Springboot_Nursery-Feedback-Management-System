package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;



@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public User addUser(User user) {
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public Optional<User> getUserById(int id) {
        return repo.findById(id);
    }

    public Optional<User> updateUser(int id, User user) {
        return repo.findById(id).map(existing -> {
            existing.setUsername(user.getUsername());
            existing.setEmail(user.getEmail());
            existing.setRole(user.getRole());
            return repo.save(existing);
        });
    }

    public List<User> getByUsernameAndRole(String username, String role) {
        return repo.findByUsernameAndRole(username, role);
    }

    public List<User> getUsersByRole(String role) {
        return repo.findByRole(role);
    }
}
