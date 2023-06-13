package io.github.dejvvit13.springjunitmockitotests.gymmember.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import io.github.dejvvit13.springjunitmockitotests.gymmember.service.GymMemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class GymMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GymMemberService gymMemberService;

    @Autowired
    private ObjectMapper objectMapper;
    private GymMember gymMember1;
    private GymMember gymMember2;

    @BeforeEach
    public void init() {
        gymMember1 = GymMember.builder()
                .id(1L)
                .firstName("Gym")
                .email("member@gmail.com")
                .lastName("Member")
                .yearsOfExperience(5)
                .build();
        gymMember2 = GymMember.builder()
                .id(2L)
                .firstName("Gym 2")
                .email("member2@gmail.com")
                .lastName("Member 2")
                .yearsOfExperience(2)
                .build();
    }

    @Test
    @DisplayName("Create Gym Member")
    void givenGymMember_whenCreate_thenReturnSavedMember() throws Exception {
        // given - precondition or setup
        given(gymMemberService.saveGymMember(ArgumentMatchers.any(GymMember.class)))
                .willAnswer(i -> i.getArgument(0));
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

    @Test
    @DisplayName("Get all Gym Members")
    void given_whenGetAllGymMembers_thenReturnAllGymMembers() throws Exception {
        // given - precondition or setup
        List<GymMember> gymMemberList = new ArrayList<>(List.of(gymMember1, gymMember2));
        given(gymMemberService.getAllGymMembers()).willReturn(gymMemberList);
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/gymMembers"));
        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    @DisplayName("Get Existing GymMember By Id")
    void givenId_whenGetGymMemberById_thenReturnGymMember() throws Exception {
        // given - precondition or setup
        Long gymMemberId = 1L;
        given(gymMemberService.getGymMemberById(gymMemberId)).willReturn(Optional.of(gymMember1));
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/gymMembers/{id}", gymMemberId));
        // then - verify the output
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(gymMember1.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(gymMember1.getLastName())))
                .andExpect(jsonPath("$.email", is(gymMember1.getEmail())));
    }

    @Test
    @DisplayName("Get non existing GymMember by id")
    void givenId_whenGetGymMemberById_thenResponseStatusNotFound() throws Exception {
        // given - precondition or setup
        Long gymMemberId = 5L;
        given(gymMemberService.getGymMemberById(gymMemberId)).willReturn(Optional.empty());
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/gymMembers/{id}", gymMemberId));
        // then - verify the output
        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Update existing Gym Member")
    void givenGymMember_whenUpdate_thenReturnUpadtedGymMember() throws Exception {
        // given - precondition or setup
        Long gymMemberId = 1L;
        given(gymMemberService.getGymMemberById(gymMemberId)).willReturn(Optional.of(gymMember1));
        given(gymMemberService.updateGymMember(any(GymMember.class)))
                .willAnswer(i -> i.getArgument(0));
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/gymMembers/{id}", gymMemberId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gymMember2))
        );
        // then - verify the output
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(gymMember2.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(gymMember2.getLastName())))
                .andExpect(jsonPath("$.email", is(gymMember2.getEmail())));
    }

    @Test
    @DisplayName("Update non existing Gym Member")
    void givenGymMember_whenUpdate_thenReturn404() throws Exception {
        // given - precondition or setup
        Long gymMemberId = 4L;
        given(gymMemberService.getGymMemberById(gymMemberId)).willReturn(Optional.empty());
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/gymMembers/{id}", gymMemberId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gymMember2))
        );
        // then - verify the output
        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete existing gym member")
    void givenGymMemberId_whenDelete_thenReturn200() throws Exception {
        // given - precondition or setup
        Long gymMemberId = 1L;
        willDoNothing().given(gymMemberService).deleteEmployee(gymMemberId);
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/gymMembers/{id}", gymMemberId));
        // then - verify the output
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Deleted member with id: " + gymMemberId)));
    }


}