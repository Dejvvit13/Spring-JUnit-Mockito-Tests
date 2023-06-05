package io.github.dejvvit13.springjunitmockitotests.gymmember.repository;

import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class GymMemberRepositoryTest {

    private final GymMemberRepository gymMemberRepository;



    @Test
    @DisplayName("Save and Return Employee")
    void givenEmployee_whenSave_thenReturnSavedEmployee() {
        // given - precondition or setup
        GymMember gymMember = GymMember.builder()
                .firstName("Gym")
                .lastName("Member")
                .yearsOfExperience(5)
                .build();
        // when - action or the behaviour that we are going test
        GymMember savedGymMember = gymMemberRepository.save(gymMember);
        // then - verify the output
        assertThat(savedGymMember).isNotNull();
        assertThat(savedGymMember.getId()).isPositive();
    }
    @Test
    @DisplayName("")
    void given_when_then() {
        // given - precondition or setup
        GymMember gymMember1 = GymMember.builder()
                .firstName("Gym")
                .lastName("Member")
                .yearsOfExperience(5)
                .build();
        GymMember gymMember2 = GymMember.builder()
                .firstName("Gym 2")
                .lastName("Member 2")
                .yearsOfExperience(2)
                .build();

        gymMemberRepository.save(gymMember1);
        gymMemberRepository.save(gymMember2);
        // when - action or the behaviour that we are going test
        List<GymMember> members = gymMemberRepository.findAll();
        // then - verify the output
        assertThat(members).isNotNull();
        assertThat(members.size()).isEqualTo(2);
    }

}