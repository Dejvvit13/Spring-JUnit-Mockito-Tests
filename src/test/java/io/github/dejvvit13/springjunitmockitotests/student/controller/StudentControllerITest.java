package io.github.dejvvit13.springjunitmockitotests.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dejvvit13.springjunitmockitotests.student.model.Student;
import io.github.dejvvit13.springjunitmockitotests.student.repository.StudentRepository;
import io.github.dejvvit13.springjunitmockitotests.testcontainers.AbstractionBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StudentControllerITest extends AbstractionBaseTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StudentRepository studentRepository;

    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
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
    @DisplayName("Get All Students")
    void givenStudents_whenGetAllStudents_thenReturnListOfStudents() throws Exception {
        // given - precondition or setup
        studentRepository.saveAll(List.of(student1, student2));
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/students"));
        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    @DisplayName("Create Student")
    void givenStudent_whenCreate_thenReturnStudent() throws Exception {
        // given - precondition or setup
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student1)));
        // then - verify the output
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(student1.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(student1.getLastName())))
                .andExpect(jsonPath("$.email", is(student1.getEmail())));
    }
}