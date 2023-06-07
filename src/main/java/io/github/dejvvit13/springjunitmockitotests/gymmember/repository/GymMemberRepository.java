package io.github.dejvvit13.springjunitmockitotests.gymmember.repository;

import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymMemberRepository extends JpaRepository<GymMember, Long> {

    Optional<GymMember> findByEmail(String email);

    @Query("select g from GymMember g where g.firstName = ?1 and g.lastName = ?2")
    GymMember findByJPQL(String firstName,String lastName);

    @Query("select g from GymMember g where g.firstName =:firstName and g.lastName =: lastName")
    GymMember findByJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);
}