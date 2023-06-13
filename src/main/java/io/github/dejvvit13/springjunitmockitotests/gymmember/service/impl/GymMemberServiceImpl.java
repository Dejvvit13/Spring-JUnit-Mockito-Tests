package io.github.dejvvit13.springjunitmockitotests.gymmember.service.impl;

import io.github.dejvvit13.springjunitmockitotests.gymmember.exceptions.ResourceNotFoundException;
import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import io.github.dejvvit13.springjunitmockitotests.gymmember.repository.GymMemberRepository;
import io.github.dejvvit13.springjunitmockitotests.gymmember.service.GymMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GymMemberServiceImpl implements GymMemberService {

    private final GymMemberRepository gymMemberRepository;

    @Override
    public GymMember saveGymMember(GymMember gymMember) {
        if (gymMemberRepository.existsByEmail(gymMember.getEmail())) {
            throw new ResourceNotFoundException("Member with given email already exists");
        }
        return gymMemberRepository.save(gymMember);
    }

    @Override
    public List<GymMember> getAllGymMembers() {
        return gymMemberRepository.findAll();
    }

    @Override
    public Optional<GymMember> getGymMemberById(Long id) {
        return gymMemberRepository.findById(id);
    }

    @Override
    public GymMember updateGymMember(GymMember updatedGymMember) {
        return gymMemberRepository.save(updatedGymMember);
    }

    @Override
    public void deleteEmployee(Long id) {
        gymMemberRepository.deleteById(id);
    }


}
