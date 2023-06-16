package io.github.dejvvit13.springjunitmockitotests.student.repository;

import io.github.dejvvit13.springjunitmockitotests.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

}