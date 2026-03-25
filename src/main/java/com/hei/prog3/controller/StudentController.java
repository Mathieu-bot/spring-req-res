package com.hei.prog3.controller;

import com.hei.prog3.entity.Student;
import com.hei.prog3.service.StudentService;
import com.hei.prog3.validator.StudentValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final StudentValidator studentValidator;

    public StudentController(StudentService studentService, StudentValidator studentValidator) {
        this.studentService = studentService;
        this.studentValidator = studentValidator;
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudents(@RequestBody List<Student> newStudents) {
        List<String> errors = studentValidator.validateList(newStudents);

        if (!errors.isEmpty()) {
            String errorMessage = String.join("; ", errors);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            List<Student> students = studentService.addStudents(newStudents);
            return ResponseEntity.status(HttpStatus.CREATED).body(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request");
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