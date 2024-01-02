package com.didado.armory.domain.profile.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "armory_stat")
@Getter
@ToString(of = {"id", "type", "value"})
public class Stat {
    @Id
    @GeneratedValue
    @Column(name = "stat_id")
    private Long id;
    private String type;

    @Column(name = "amount")
    private String value;

    @ElementCollection
    private List<String> toolTip = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_profile_id")
    private ArmoryProfile armoryProfile;

    protected Stat() {
    }

    @Builder
    public Stat(String type, String value, List<String> toolTip) {
        this.type = type;
        this.value = value;
        this.toolTip = toolTip;
    }

    public void changeArmoryProfile(ArmoryProfile armoryProfile) {
        this.armoryProfile = armoryProfile;
        armoryProfile.getStats().add(this);
    }

    public void changeData(Stat stat) {
        this.type = stat.getType();
        this.value = stat.getValue();
        this.toolTip = stat.getToolTip();
    }
}
