package io.github.dejvvit13.springjunitmockitotests.student.repository;

import io.github.dejvvit13.springjunitmockitotests.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}