package io.github.dejvvit13.springjunitmockitotests.student.repository;

import io.github.dejvvit13.springjunitmockitotests.student.model.Student;
import io.github.dejvvit13.springjunitmockitotests.testcontainers.AbstractionBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryITest extends AbstractionBaseTest {

    @Autowired
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
    @DisplayName("Save student")
    void givenStudent_whenSave_thenReturnSavedStudent() {
        // given - precondition or setup
        // when - action or the behaviour that we are going test
        Student savedStudent = studentRepository.save(student1);
        // then - verify the output
        assertThat(savedStudent).isNotNull();
    }

    @Test
    @DisplayName("Find all students")
    void given2Students_whenSaved_thenReturnListOf2Students() {
        // given - precondition or setup
        studentRepository.saveAll(List.of(student1, student2));
        // when - action or the behaviour that we are going test
        List<Student> students = studentRepository.findAll();
        // then - verify the output
        assertThat(students).hasSize(2);
    }

    @Test
    @DisplayName("Find student by id")
    void given_when_then() {
        // given - precondition or setup
        Student savedStudent = studentRepository.save(student1);
        // when - action or the behaviour that we are going test
        Student resultStudent = studentRepository.findById(savedStudent.getId()).get();
        // then - verify the output
        assertThat(resultStudent).isNotNull();
    }

    @Test
    @DisplayName("Update student")
    void givenStudent_whenUpdate_thenReturnUpdatedStudent() {
        // given - precondition or setup
        Student saved = studentRepository.save(student1);
        saved.setFirstName("Dawid");
        // when - action or the behaviour that we are going test
        Student result = studentRepository.save(saved);
        // then - verify the output
        assertThat(result.getFirstName()).isEqualTo("Dawid");
    }

    @Test
    @DisplayName("Delete student")
    void givenStudent_whenDelete_thenNothing() {
        // given - precondition or setup
        studentRepository.save(student1);
        // when - action or the behaviour that we are going test
        studentRepository.deleteById(student1.getId());
        Optional<Student> emptyStudent = studentRepository.findById(student1.getId());
        // then - verify the output
        assertThat(emptyStudent).isEmpty();
    }
}