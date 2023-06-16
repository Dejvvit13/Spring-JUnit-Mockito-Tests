package io.github.dejvvit13.springjunitmockitotests.student.service.impl;

import io.github.dejvvit13.springjunitmockitotests.student.exception.CreateEntityException;
import io.github.dejvvit13.springjunitmockitotests.student.model.Student;
import io.github.dejvvit13.springjunitmockitotests.student.repository.StudentRepository;
import io.github.dejvvit13.springjunitmockitotests.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student studentToCreate) {
        Optional<Student> student = studentRepository.findByEmail(studentToCreate.getEmail());
        if (student.isPresent()) {
            throw new CreateEntityException("Student with this email already exists");
        }
        return studentRepository.save(studentToCreate);
    }

}
