package com.hei.prog3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Parameter 'name' is required");
        }
        return ResponseEntity.ok("Welcome " + name);
    }
}