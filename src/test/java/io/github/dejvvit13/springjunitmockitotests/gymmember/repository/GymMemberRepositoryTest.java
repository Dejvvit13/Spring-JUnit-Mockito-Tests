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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class GymMemberRepositoryTest {

    private final GymMemberRepository gymMemberRepository;
    GymMember gymMember1;
    GymMember gymMember2;

    @BeforeEach
    void setup() {
        gymMember1 = GymMember.builder()
                .firstName("Gym")
                .email("member@gmail.com")
                .lastName("Member")
                .yearsOfExperience(5)
                .build();
        gymMember2 = GymMember.builder()
                .firstName("Gym 2")
                .lastName("Member 2")
                .yearsOfExperience(2)
                .build();
    }

    @Test
    @DisplayName("Save and Return Member")
    void givenMember_whenSave_thenReturnSavedMember() {
        // given - precondition or setup
        // when - action or the behaviour that we are going test
        GymMember savedGymMember = gymMemberRepository.save(gymMember1);
        // then - verify the output
        assertThat(savedGymMember).isNotNull();
        assertThat(savedGymMember.getId()).isPositive();
    }

    @Test
    @DisplayName("Get List of GymMembers")
    void givenMember_whenGetAll_thenReturnListOfTwo() {
        // given - precondition or setup
        gymMemberRepository.save(gymMember1);
        gymMemberRepository.save(gymMember2);
        // when - action or the behaviour that we are going test
        List<GymMember> members = gymMemberRepository.findAll();
        // then - verify the output
        assertThat(members).isNotNull();
        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Get GymMember by Id")
    void givenMember_whenFindById_thenReturnEmployeeOfId() {
        // given - precondition or setup
        gymMemberRepository.save(gymMember1);
        // when - action or the behaviour that we are going test
        GymMember resultMember = gymMemberRepository.findById(gymMember1.getId()).get();
        // then - verify the output
        assertThat(resultMember).isNotNull();
    }

    @Test
    @DisplayName("Find member by email")
    void givenMemberEmail_whenFindByEmail_thenReturnMember() {
        // given - precondition or setup
        gymMemberRepository.save(gymMember1);
        // when - action or the behaviour that we are going test
        GymMember result = gymMemberRepository.findByEmail(gymMember1.getEmail()).get();
        // then - verify the output
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(gymMember1.getEmail());
    }

    @Test
    @DisplayName("Update gym member operation")
    void givenGymMemberObejct_whenUpdateMember_thenReturnUpdatedMember() {
        // given - precondition or setup
        gymMemberRepository.save(gymMember1);
        // when - action or the behaviour that we are going test
        GymMember savedMember = gymMemberRepository.findById(gymMember1.getId()).get();
        savedMember.setFirstName("Updated");
        GymMember result = gymMemberRepository.save(savedMember);
        // then - verify the output
        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("Updated");
    }

    @Test
    @DisplayName("Remove Gym Member Object")
    void givenGymMemberObject_whenDelete_thenRemoveGymMember() {
        // given - precondition or setup
        gymMemberRepository.save(gymMember1);
        // when - action or the behaviour that we are going test
        gymMemberRepository.deleteById(gymMember1.getId());
        Optional<GymMember> member = gymMemberRepository.findById(gymMember1.getId());
        // then - verify the output
        assertThat(member).isEmpty();
    }

    @Test
    @DisplayName("Find Gym Member by custom JPQL query with index params")
    void givenFirstNameAndLastName_whenFindByCustomJPQLIndexParams_thenReturnGymMember() {
        // given - precondition or setup
        gymMemberRepository.save(gymMember1);
        // when - action or the behaviour that we are going test
        GymMember resultMember = gymMemberRepository.findByJPQL(gymMember1.getFirstName(), gymMember1.getLastName());
        // then - verify the output
        assertThat(resultMember).isNotNull();
    }

    @Test
    @DisplayName("Find Gym Member by custom JPQL query with named params")
    void givenFirstNameAndLastName_whenFindByCustomJPQLNamedParams_thenReturnGymMember() {
        // given - precondition or setup
        gymMemberRepository.save(gymMember1);
        // when - action or the behaviour that we are going test
        GymMember resultMember = gymMemberRepository.findByJPQLNamedParams(gymMember1.getFirstName(), gymMember1.getLastName());
        // then - verify the output
        assertThat(resultMember).isNotNull();
    }

}