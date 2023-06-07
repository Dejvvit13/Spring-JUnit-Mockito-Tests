package io.github.dejvvit13.springjunitmockitotests.gymmember.service.impl;

import io.github.dejvvit13.springjunitmockitotests.gymmember.exceptions.ResourceNotFoundException;
import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import io.github.dejvvit13.springjunitmockitotests.gymmember.repository.GymMemberRepository;
import io.github.dejvvit13.springjunitmockitotests.gymmember.service.GymMemberService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GymMemberServiceImpl implements GymMemberService {

    private final GymMemberRepository gymMemberRepository;

    @Override
    public GymMember createGymMember(GymMember gymMember) {
        if (gymMemberRepository.existsByEmail(gymMember.getEmail())) {
            throw new ResourceNotFoundException("Member with given email already exists");
        }
        return gymMemberRepository.save(gymMember);
    }
}
