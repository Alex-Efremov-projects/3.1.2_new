package com.example.spring.demo.service;

import com.example.spring.demo.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);
    List<Role> getListRole();
}
