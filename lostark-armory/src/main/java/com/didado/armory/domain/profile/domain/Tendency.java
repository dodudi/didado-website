package com.didado.armory.domain.profile.domain;

import com.didado.armory.domain.profile.dto.StatParameter;
import com.didado.armory.domain.profile.dto.TendencyParameter;
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

    public void changeArmoryProfile(ArmoryProfile armoryProfile) {
        this.armoryProfile = armoryProfile;
        armoryProfile.getTendencies().add(this);
    }

    public void changeData(Tendency tendency) {
        this.type = tendency.getType();
        this.point = tendency.getPoint();
        this.maxPoint = tendency.getMaxPoint();
    }
}
