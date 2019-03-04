package ru.vkurov.sonetrack.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: Remove
@RestController
@RequiredArgsConstructor
public class MainController {
    
    @GetMapping("/home")
    public String home() {
        return "Home";
    }
    
    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
    
    @GetMapping
    public String main() {
        return "Main";
    }
}
