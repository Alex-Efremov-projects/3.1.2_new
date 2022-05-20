package com.example.spring.demo.controller;

import com.example.spring.demo.model.Role;
import com.example.spring.demo.model.User;
import com.example.spring.demo.service.RoleService;
import com.example.spring.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> saveUser(@RequestBody User user, @RequestParam Long[] roleIds) {
        userService.saveUser(user, roleIds);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<Void> update(@RequestBody User user, @RequestParam Long[] roleIds) {
        userService.editUser(user, roleIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/roles")
    @ResponseBody
    public ResponseEntity<Set<Role>> roles() {
        return ResponseEntity.ok().body(roleService.getSetRole());
    }
}