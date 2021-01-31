package com.example.spring.demo.dao;

import com.example.spring.demo.model.Role;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select role from  Role role where role.role =: name",Role.class)
                .setParameter("name",name)
                .getSingleResult();
    }

    @Override
    public List<Role> getListRole() {
        return entityManager.createQuery("from Role").getResultList();
    }
}
