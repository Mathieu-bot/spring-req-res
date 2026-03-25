package com.hei.prog3.validator;

import com.hei.prog3.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentValidator {

    private static final int MIN_AGE = 14;
    private static final int MAX_AGE = 100;

    public List<String> validate(Student student) {
        List<String> errors = new ArrayList<>();

        if (student == null) {
            errors.add("Student cannot be null");
            return errors;
        }

        if (student.getFirstName() == null || student.getFirstName().isBlank()) {
            errors.add("First name is required");
        }

        if (student.getLastName() == null || student.getLastName().isBlank()) {
            errors.add("Last name is required");
        }

        if (student.getAge() < MIN_AGE || student.getAge() > MAX_AGE) {
            errors.add("Age must be between " + MIN_AGE + " and " + MAX_AGE);
        }

        return errors;
    }

    public List<String> validateList(List<Student> students) {
        List<String> errors = new ArrayList<>();

        if (students == null || students.isEmpty()) {
            errors.add("Student list cannot be empty");
            return errors;
        }

        for (int i = 0; i < students.size(); i++) {
            List<String> studentErrors = validate(students.get(i));
            for (String error : studentErrors) {
                errors.add("Student at index " + i + ": " + error);
            }
        }

        return errors;
    }
}
