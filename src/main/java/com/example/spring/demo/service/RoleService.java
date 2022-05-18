package com.example.spring.demo.service;

import com.example.spring.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRoleByName(String name);

    Set<Role> getSetRoleByIds(Long[] rolesIds);

    List<Role> getListRole();
}
