package io.github.dejvvit13.springjunitmockitotests.gymmember.service;

import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;

import java.util.List;
import java.util.Optional;

public interface GymMemberService {

    GymMember saveGymMember(GymMember gymMember);

    List<GymMember> getAllGymMembers();

    Optional<GymMember> getGymMemberById(Long id);

    GymMember updateGymMember(GymMember updatedGymMember);

    void deleteEmployee(Long id);
}
