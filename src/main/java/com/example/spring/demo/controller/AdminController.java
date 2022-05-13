package com.example.spring.demo.controller;

import com.example.spring.demo.model.User;
import com.example.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private static final String REDIRECT = "redirect:/admin";

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "index";
    }

    @GetMapping(value = "/add")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user_save_info";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return REDIRECT;
    }

    @GetMapping(value = "/edit/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user_edit";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("password") String password) {
        if (password.equals(user.getPassword())) {
            user.setPassword(user.getPassword());
        } else user.setPassword(password);
        userService.editUser(user);
        return REDIRECT;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return REDIRECT;
    }
}