package com.hei.prog3.controller;

import com.hei.prog3.entity.Student;
import com.hei.prog3.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentRepository studentRepository = new StudentRepository();

    @GetMapping("/welcome")
    public String welcome(@RequestParam("name") String name) {
        return "Welcome " + name;
    }

    @PostMapping("/students")
    public List<Student> addStudents(@RequestBody List<Student> newStudents) {
        studentRepository.addAll(newStudents);
        return studentRepository.findAll();
    }
}