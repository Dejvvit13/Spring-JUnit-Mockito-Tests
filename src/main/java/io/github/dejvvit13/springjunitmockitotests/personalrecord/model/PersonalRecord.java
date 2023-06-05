package io.github.dejvvit13.springjunitmockitotests.personalrecord.model;

import io.github.dejvvit13.springjunitmockitotests.gymmember.model.GymMember;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PersonalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private double benchPress;
    private double backSquat;
    private double deadLift;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_member_id")
    private GymMember gymMember;

}
