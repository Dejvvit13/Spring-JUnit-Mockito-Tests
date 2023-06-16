package io.github.dejvvit13.springjunitmockitotests.student.service;

import io.github.dejvvit13.springjunitmockitotests.student.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student createStudent(Student student);


}
