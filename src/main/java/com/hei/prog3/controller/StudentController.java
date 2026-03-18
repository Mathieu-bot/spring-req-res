package com.hei.prog3.controller;

import com.hei.prog3.entity.Student;
import com.hei.prog3.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam("name") String name) {
        return "Welcome " + name;
    }

    @PostMapping("/students")
    public String addStudents(@RequestBody List<Student> newStudents) {
        studentService.addStudents(newStudents);
        return studentService.getStudentsNamesAsString();
    }

    @GetMapping("/students")
    public String getStudents(@RequestHeader("Accept") String acceptHeader) {
        if ("text/plain".equals(acceptHeader)) {
            return studentService.getStudentsNamesAsString();
        } else {
            return "Not supported format";
        }
    }
}