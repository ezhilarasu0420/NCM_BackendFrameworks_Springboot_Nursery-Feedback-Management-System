package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return service.getUserById(id)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable int id,
            @RequestBody User user) {

        return service.updateUser(id, user)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    
@GetMapping("/username/{username}/role/{role}")
public ResponseEntity<List<User>> getByUsernameAndRole(
        @PathVariable String username,
        @PathVariable String role) {

    return new ResponseEntity<>(
            service.getByUsernameAndRole(username, role),
            HttpStatus.OK);
}

@GetMapping("/role/{role}")
public ResponseEntity<?> getUsersByRole(@PathVariable String role) {

    List<User> users = service.getUsersByRole(role);

    if (users.isEmpty()) {
        return new ResponseEntity<>(
                "No users found with role: " + role,
                HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(users, HttpStatus.OK);
}

}
