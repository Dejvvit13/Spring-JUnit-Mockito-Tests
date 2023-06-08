package io.github.dejvvit13.springjunitmockitotests.gymmember.service;

import io.github.dejvvit13.springjunitmockitotests.gymmember.exceptions.ResourceNotFoundException;
import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import io.github.dejvvit13.springjunitmockitotests.gymmember.repository.GymMemberRepository;
import io.github.dejvvit13.springjunitmockitotests.gymmember.service.impl.GymMemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GymMemberServiceTest {

    @Mock
    private GymMemberRepository gymMemberRepository;
    @InjectMocks
    private GymMemberServiceImpl gymMemberService;
    GymMember gymMember1;
    GymMember gymMember2;

    @BeforeEach
    public void setup() {
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
                .lastName("Member 2")
                .yearsOfExperience(2)
                .build();
    }

    @Test
    @DisplayName("Create GymMember Positive Case")
    void givenGymMemberObject_whenCreated_thenReturnGymMemberObject() {
        // given - precondition or setup
        given(gymMemberRepository.existsByEmail(gymMember1.getEmail())).willReturn(false);
        given(gymMemberRepository.save(gymMember1)).willReturn(gymMember1);
        // when - action or the behaviour that we are going test
        GymMember savedMember = gymMemberService.saveGymMember(gymMember1);
        // then - verify the output
        Assertions.assertThat(savedMember).isNotNull();
    }

    @Test
    @DisplayName("Create GymMember Negative Case")
    void givenGymMemberObject_whenCreated_thenThrowsException() {
        // given - precondition or setup
        given(gymMemberRepository.existsByEmail(gymMember1.getEmail())).willReturn(true);
        // when - action or the behaviour that we are going test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> gymMemberService.saveGymMember(gymMember1));
        // then - verify the output
    }

    @Test
    @DisplayName("Get all gym members list")
    void givenGymMembersList_whenGetAllGymMembers_thenReturnListOfAllGymMembers() {
        // given - precondition or setup
        given(gymMemberRepository.findAll()).willReturn(List.of(gymMember1, gymMember2));
        // when - action or the behaviour that we are going test
        List<GymMember> gymMembers = gymMemberService.getAllGymMembers();
        // then - verify the output
        Assertions.assertThat(gymMembers).hasSize(2);
    }

    @Test
    @DisplayName("Get empty gym members list")
    void givenEmpltyGymMembersList_whenGetAllGymMembers_thenReturnEmptyListOfAllGymMembers() {
        // given - precondition or setup
        given(gymMemberRepository.findAll()).willReturn(Collections.emptyList());
        // when - action or the behaviour that we are going test
        List<GymMember> gymMembers = gymMemberService.getAllGymMembers();
        // then - verify the output
        Assertions.assertThat(gymMembers).isEmpty();
    }

    @Test
    @DisplayName("Get GymMember by id")
    void givenGymMember_whenGetById_thenReturnGymMemberObject() {
        // given - precondition or setup
        given(gymMemberRepository.findById(1L)).willReturn(Optional.of(gymMember1));
        // when - action or the behaviour that we are going test
        GymMember fetchedGymMember = gymMemberService.getGymMemberById(1L).get();
        // then - verify the output
        Assertions.assertThat(fetchedGymMember).isNotNull();
    }

    @Test
    @DisplayName("Update GymMember")
    void givenGymMember_whenUpdate_thenReturnUpdatedGymMember() {
        // given - precondition or setup
        given(gymMemberRepository.save(gymMember1)).willReturn(gymMember1);
        gymMember1.setFirstName("UpdatedGym");
        // when - action or the behaviour that we are going test
        GymMember updatedMember = gymMemberService.updateGymMember(gymMember1);
        // then - verify the output
        Assertions.assertThat(updatedMember.getFirstName()).isEqualTo("UpdatedGym");
    }


}