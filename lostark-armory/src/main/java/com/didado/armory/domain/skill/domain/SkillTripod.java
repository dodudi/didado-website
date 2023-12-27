package com.didado.armory.domain.skill.domain;

import com.didado.armory.domain.skill.dto.SkillTripodParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class SkillTripod {
    @Id
    @GeneratedValue
    @Column(name = "skill_tripod_id")
    private Long id;
    private Integer tier;
    private Integer slot;
    private String name;
    private String icon;
    private Integer level;
    private Boolean isSelected;

    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_skill_id")
    private ArmorySkill armorySkill;

    protected SkillTripod() {
    }

    @Builder
    public SkillTripod(Integer tier, Integer slot, String name, String icon, Integer level, Boolean isSelected, String toolTip) {
        this.tier = tier;
        this.slot = slot;
        this.name = name;
        this.icon = icon;
        this.level = level;
        this.isSelected = isSelected;
        this.toolTip = toolTip;
    }

    public void changeArmorySkill(ArmorySkill armorySkill) {
        this.armorySkill = armorySkill;
        armorySkill.getTripods().add(this);
    }
}
