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
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String acceptHeader) {
        try {
            if (acceptHeader == null || acceptHeader.trim().isEmpty() || "*/*".equals(acceptHeader)) {
                return ResponseEntity.badRequest().body("Accept header is required and must be text/plain or application/json");
            }

            if (!"text/plain".equals(acceptHeader) && !"application/json".equals(acceptHeader)) {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Unsupported media type: " + acceptHeader);
            }

            List<Student> students = studentService.getAllStudents();
            return ResponseEntity.ok().body(students);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }
}