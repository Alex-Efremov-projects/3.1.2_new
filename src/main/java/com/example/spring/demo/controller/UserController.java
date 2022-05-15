package com.example.spring.demo.controller;

import com.example.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }
    @GetMapping("/login")
    public String getHomePage() {
        return "login";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("oneUser", userService.getUserByEmail(principal.getName()));
        return "user";
    }
}
