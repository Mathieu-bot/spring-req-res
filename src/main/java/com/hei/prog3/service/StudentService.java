package com.hei.prog3.service;

import com.hei.prog3.entity.Student;
import com.hei.prog3.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> addStudents(List<Student> students) {
        studentRepository.addAll(students);
        return studentRepository.findAll();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String getStudentsNamesAsString() {
        StringBuilder names = new StringBuilder();
        for (Student student : studentRepository.findAll()) {
            names.append(student.getFirstName()).append(" ").append(student.getLastName()).append("\n");
        }
        return names.toString();
    }
}