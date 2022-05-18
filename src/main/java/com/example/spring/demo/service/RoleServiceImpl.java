package com.example.spring.demo.service;

import com.example.spring.demo.dao.RoleDao;
import com.example.spring.demo.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public Set<Role> getSetRoleByIds(Long[] rolesIds) {
        return roleDao.getSetRoleById(rolesIds);
    }

    @Override
    public List<Role> getListRole() {
        return roleDao.getListRole();
    }
}
