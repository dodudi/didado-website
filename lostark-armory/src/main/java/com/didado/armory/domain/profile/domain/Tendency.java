package com.didado.armory.domain.profile.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString(exclude = "armoryProfile")
public class Tendency {
    @Id
    @GeneratedValue
    @Column(name = "tendency_id")
    private Long id;

    private String type;

    private Integer point;

    private Integer maxPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_profile_id")
    private ArmoryProfile armoryProfile;

    protected Tendency() {
    }

    @Builder
    public Tendency(String type, Integer point, Integer maxPoint) {
        this.type = type;
        this.point = point;
        this.maxPoint = maxPoint;
    }

    public Tendency updateArmoryProfile(ArmoryProfile armoryProfile) {
        this.armoryProfile = armoryProfile;
        return this;
    }
}
