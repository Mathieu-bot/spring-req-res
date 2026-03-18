package com.hei.prog3.repository;

import com.hei.prog3.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private final List<Student> students = new ArrayList<>();

    public List<Student> findAll() {
        return students;
    }

    public void addAll(List<Student> newStudents) {
        students.addAll(newStudents);
    }
}