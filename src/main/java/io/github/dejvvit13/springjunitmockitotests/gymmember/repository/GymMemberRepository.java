package io.github.dejvvit13.springjunitmockitotests.gymmember.repository;

import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymMemberRepository extends JpaRepository<GymMember, Long> {
}