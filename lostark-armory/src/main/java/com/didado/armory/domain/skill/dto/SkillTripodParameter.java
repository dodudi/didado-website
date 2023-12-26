package com.didado.armory.domain.skill.dto;

import com.didado.armory.domain.skill.domain.ArmorySkill;
import com.didado.armory.domain.skill.domain.SkillTripod;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SkillTripodParameter {
    @JsonProperty(value = "Tier")
    private Integer tier;

    @JsonProperty(value = "Slot")
    private Integer slot;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Level")
    private Integer level;

    @JsonProperty(value = "IsSelected")
    private Boolean isSelected;

    @JsonProperty(value = "Tooltip")
    private String toolTip;

    public SkillTripod toSkillTripod(ArmorySkill armorySkill) {
        SkillTripod skillTripod = new SkillTripod(this);
        skillTripod.updateArmorySkill(armorySkill);
        return skillTripod;
    }
}
