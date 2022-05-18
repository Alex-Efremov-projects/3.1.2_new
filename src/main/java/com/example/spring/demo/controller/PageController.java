package com.example.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "index";

    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("")
    public String homePage() {
        return "login";
    }
}
