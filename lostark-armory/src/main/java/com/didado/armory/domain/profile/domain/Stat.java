package com.didado.armory.domain.profile.domain;

import com.didado.armory.domain.dto.armory.Armory;
import com.didado.armory.domain.profile.dto.ArmoryProfileParameter;
import com.didado.armory.domain.profile.dto.StatParameter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "armory_stat")
@Getter
@ToString(exclude = "armoryProfile")
public class Stat {
    @Id
    @GeneratedValue
    @Column(name = "stat_id")
    private Long id;
    private String type;
    private String amount;

    @ElementCollection
    private List<String> toolTip = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_profile_id")
    private ArmoryProfile armoryProfile;

    protected Stat() {
    }

    @Builder
    public Stat(String type, String amount) {
        this.type = type;
        this.amount = amount;
    }

    public Stat updateData(StatParameter parameter) {
        this.type = parameter.getType();
        this.amount = parameter.getValue();
        this.toolTip.clear();
        this.toolTip.addAll(parameter.getToolTip());
        return this;
    }

    public Stat updateArmoryProfile(ArmoryProfile armoryProfile) {
        this.armoryProfile = armoryProfile;
        return this;
    }
}
