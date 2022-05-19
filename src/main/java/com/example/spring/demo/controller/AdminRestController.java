package com.example.spring.demo.controller;

import com.example.spring.demo.model.User;
import com.example.spring.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminRestController {
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<Void> update(@RequestBody User user, @PathVariable("id") long id) {
        user.setId(id);
        userService.editUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}