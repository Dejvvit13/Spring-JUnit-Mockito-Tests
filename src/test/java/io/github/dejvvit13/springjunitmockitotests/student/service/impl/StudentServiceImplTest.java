package io.github.dejvvit13.springjunitmockitotests.student.service.impl;

import io.github.dejvvit13.springjunitmockitotests.student.exception.CreateEntityException;
import io.github.dejvvit13.springjunitmockitotests.student.model.Student;
import io.github.dejvvit13.springjunitmockitotests.student.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        student1 = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .build();

        student2 = Student.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("jane@gmail.com")
                .build();

    }

    @Test
    @DisplayName("Get all students")
    void givenTwoStudents_whenGetAllStudents_thenReturnListOf2() {
        // given - precondition or setup
        given(studentRepository.findAll()).willReturn(List.of(student1, student2));
        // when - action or the behaviour that we are going test
        List<Student> result = studentService.getAllStudents();
        // then - verify the output
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("Create Student - positive case")
    void givenStudent_whenCreate_thenReturnStudent() {
        // given - precondition or setup
        given(studentRepository.findByEmail(student1.getEmail())).willReturn(Optional.empty());
        given(studentRepository.save(student1)).willReturn(student1);
        // when - action or the behaviour that we are going test
        Student saved = studentService.createStudent(student1);
        // then - verify the output
        assertThat(saved).isNotNull();
    }

    @Test
    @DisplayName("Create Student - negative case")
    void givenStudent_whenCreate_thenThrowException() {
        // given - precondition or setup
        given(studentRepository.findByEmail(student1.getEmail())).willReturn(Optional.of(student1));
        // when - action or the behaviour that we are going test
        Assertions.assertThrows(CreateEntityException.class,()->studentService.createStudent(student1));
        // then - verify the output
    }


}