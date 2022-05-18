package com.example.spring.demo.service;

import com.example.spring.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(long id);

    void deleteUser(long id);

    void editUser(User user);

    User getUserByEmail(String email);
}

