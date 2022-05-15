package com.example.spring.demo.service;

import com.example.spring.demo.dao.RoleDao;
import com.example.spring.demo.dao.UserDao;
import com.example.spring.demo.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;

    private final BCryptPasswordEncoder passwordEncoder;

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
    public void saveUser(User user, Long[] roleIds) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleSet(roleDao.getSetRoleById(roleIds));
        userDao.saveUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void editUser(User user, Long[] roleIds) {
        User userDB = userDao.getUser(user.getId());
        if (!userDB.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoleSet(roleDao.getSetRoleById(roleIds));
        userDao.editUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.showUserByUsername(email);
    }
}
