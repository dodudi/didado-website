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

    public SkillTripod(SkillTripodParameter skillTripodParameter) {
        this.tier = skillTripodParameter.getTier();
        this.slot = skillTripodParameter.getSlot();
        this.name = skillTripodParameter.getName();
        this.icon = skillTripodParameter.getIcon();
        this.level = skillTripodParameter.getLevel();
        this.isSelected = skillTripodParameter.getIsSelected();
        this.toolTip = skillTripodParameter.getToolTip();
    }

    public void updateArmorySkill(ArmorySkill armorySkill) {
        this.armorySkill = armorySkill;
    }
}
