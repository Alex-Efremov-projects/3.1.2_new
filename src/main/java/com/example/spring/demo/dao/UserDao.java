package com.example.spring.demo.dao;



import com.example.spring.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int id);

    void editUser(User user);

    void deleteUser(int id);

    User showUserByUsername(String email);



//перенес логику в RoleDao

/*    Role getRoleByName(String name);

    List<Role> getListRole();*/

}

