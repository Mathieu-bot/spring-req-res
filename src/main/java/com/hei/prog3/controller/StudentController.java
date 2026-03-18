package com.hei.prog3.controller;

import com.hei.prog3.entity.Student;
import com.hei.prog3.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Parameter 'name' is required");
        }
        return ResponseEntity.ok("Welcome " + name);
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudents(@RequestBody List<Student> newStudents) {
        try {
            List<Student> students = studentService.addStudents(newStudents);
            return ResponseEntity.status(HttpStatus.CREATED).body(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
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