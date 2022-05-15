package com.example.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hot")
public class HotController {
    @GetMapping
    public String showHotPAge() {
        return "18+";
    }
}
