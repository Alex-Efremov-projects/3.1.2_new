package com.example.spring.demo.service;

import com.example.spring.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);

    void editUser(User user);

    User showUserByUsername(String email);
}

