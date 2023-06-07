package io.github.dejvvit13.springjunitmockitotests.gymmember.service;

import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import io.github.dejvvit13.springjunitmockitotests.gymmember.repository.GymMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class GymMemberServiceTest {

    @Mock
    private GymMemberRepository gymMemberRepository;
    @InjectMocks
    private GymMemberService gymMemberService;

    @BeforeEach
    public void setup() {
    }

    @Test
    @DisplayName("Create GymMember if not exists")
    void givenGymMemberObject_whenCreated_thenReturnGymMemberObject() {
        // given - precondition or setup
        GymMember gymMember1 = GymMember.builder()
                .id(1L)
                .firstName("Gym")
                .email("member@gmail.com")
                .lastName("Member")
                .yearsOfExperience(5)
                .build();
        BDDMockito.given(gymMemberRepository.existsByEmail(gymMember1.getEmail())).willReturn(false);
        BDDMockito.given(gymMemberRepository.save(gymMember1)).willReturn(gymMember1);
        // when - action or the behaviour that we are going test
        GymMember savedMember = gymMemberService.createGymMember(gymMember1);
        // then - verify the output
        Assertions.assertThat(savedMember).isNotNull();

    }
}