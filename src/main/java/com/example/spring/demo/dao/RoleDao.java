package com.example.spring.demo.dao;

import com.example.spring.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Role getRoleByName(String name);

    List<Role> getListRole();

    Set<Role> getSetRoleById(Long[] rolesIds);
}
