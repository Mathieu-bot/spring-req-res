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
    public String addStudents(@RequestBody List<Student> newStudents) {
        studentRepository.addAll(newStudents);
        StringBuilder names = new StringBuilder();
        for (Student student : studentRepository.findAll()) {
            names.append(student.getFirstName()).append(" ").append(student.getLastName()).append("\n");
        }
        return names.toString();
    }

    @GetMapping("/students")
    public String getStudents(@RequestHeader("Accept") String acceptHeader) {
        if ("text/plain".equals(acceptHeader)) {
            StringBuilder names = new StringBuilder();
            for (Student student : studentRepository.findAll()) {
                names.append(student.getFirstName()).append(" ").append(student.getLastName()).append("\n");
            }
            return names.toString();
        } else {
            return "Not supported format";
        }
    }
}