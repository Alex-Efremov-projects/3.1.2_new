package com.example.spring.demo.service;

import com.example.spring.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user, Long[] rolesIds);

    User getUserById(long id);

    void deleteUser(long id);

    void editUser(User user, Long[] rolesIds);

    User getUserByEmail(String email);
}

