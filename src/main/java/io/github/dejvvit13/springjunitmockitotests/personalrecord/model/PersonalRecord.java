package io.github.dejvvit13.springjunitmockitotests.personalrecord.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PersonalRecord {
    private double benchPress;
    private double backSquat;
    private double deadLift;


}
