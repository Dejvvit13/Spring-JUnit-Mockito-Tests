package io.github.dejvvit13.springjunitmockitotests.gymmember.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import io.github.dejvvit13.springjunitmockitotests.gymmember.repository.GymMemberRepository;
import io.github.dejvvit13.springjunitmockitotests.personalrecord.model.PersonalRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GymMemberControllerITests {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GymMemberRepository gymMemberRepository;
    @Autowired
    private ObjectMapper objectMapper;
    private GymMember gymMember1;
    private GymMember gymMember2;

    @BeforeEach
    void setup() {
        gymMemberRepository.deleteAll();
        gymMember1 = GymMember.builder()
                .id(1L)
                .firstName("Gym")
                .email("member@gmail.com")
                .lastName("Member")
                .yearsOfExperience(5)
                .personalRecord(new PersonalRecord())
                .build();
        gymMember2 = GymMember.builder()
                .id(2L)
                .firstName("Gym 2")
                .email("member2@gmail.com")
                .lastName("Member 2")
                .yearsOfExperience(2)
                .personalRecord(new PersonalRecord())
                .build();
    }

    @Test
    @DisplayName("Create Gym Member")
    void givenGymMember_whenCreate_thenReturnSavedMember() throws Exception {
        // given - precondition or setup
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/gymMembers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gymMember1)));
        // then - verify the output
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(gymMember1.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(gymMember1.getLastName())))
                .andExpect(jsonPath("$.email", is(gymMember1.getEmail())));
    }

}
