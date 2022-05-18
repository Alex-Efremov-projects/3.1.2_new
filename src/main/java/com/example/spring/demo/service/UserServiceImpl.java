package com.example.spring.demo.service;

import com.example.spring.demo.dao.RoleDao;
import com.example.spring.demo.dao.UserDao;
import com.example.spring.demo.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserDao userDao;
    private final RoleDao roleDao;

    private final BCryptPasswordEncoder passwordEncoder ;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleDao.getRoleByName("ROLE_USER")));
        userDao.saveUser(user);
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        User userDB = userDao.getUser(user.getId());
        if (user.getPassword().equals("")) {
            user.setPassword(userDB.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(userDB.getRoles());
        userDao.editUser(user);
    }

    @Override
    public User getUserByUsername(String email) {
        return userDao.getUserWithRolesByEmail(email);
    }

}
