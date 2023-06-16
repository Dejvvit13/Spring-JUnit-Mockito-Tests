package io.github.dejvvit13.springjunitmockitotests.gymmember.model;

import io.github.dejvvit13.springjunitmockitotests.personalrecord.model.PersonalRecord;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private int yearsOfExperience;
    private LocalDateTime lastVisit;

    @Embedded
    private PersonalRecord personalRecord;


    @PrePersist
    void prePersist() {
        lastVisit = LocalDateTime.now();
    }
}
