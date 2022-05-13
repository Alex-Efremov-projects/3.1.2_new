package com.example.spring.demo.controller;

import com.example.spring.demo.model.Role;
import com.example.spring.demo.model.User;
import com.example.spring.demo.service.RoleService;
import com.example.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ModelAndView getAllUsers(ModelAndView modelAndView, @AuthenticationPrincipal User user, Principal principal) {
        modelAndView.setViewName("index");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("listRole", roleService.getListRole());
        modelAndView.addObject("index", userService.getAllUsers());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("oneUser", userService.showUserByEmail(principal.getName()));
        return modelAndView;
    }

    @GetMapping(value = "/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("listRole", roleService.getListRole());
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("role") String[] role) {
        user.setRoleSet(getAddRole(role));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/editUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("listRole", roleService.getListRole());
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "index";
    }
    @PostMapping(value = "/userEdit")
    public String update(@ModelAttribute("user") User user, @RequestParam("role") String[] role
    ) {
        user.setRoleSet(getAddRole(role));
        userService.editUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    private Set<Role> getAddRole(String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (String s : role) {
            roleSet.add(roleService.getRoleByName(s));
        }
        return roleSet;
    }
}