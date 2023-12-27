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

    protected SkillTripodParameter() {
    }

    public SkillTripodParameter(SkillTripod skillTripod) {
        this.tier = skillTripod.getTier();
        this.slot = skillTripod.getSlot();
        this.name = skillTripod.getName();
        this.icon = skillTripod.getIcon();
        this.level = skillTripod.getLevel();
        this.isSelected = skillTripod.getIsSelected();
        this.toolTip = skillTripod.getToolTip();
    }

    public SkillTripod toSkillTripod() {
        return SkillTripod.builder()
                .tier(tier)
                .slot(slot)
                .name(name)
                .icon(icon)
                .level(level)
                .isSelected(isSelected)
                .toolTip(toolTip)
                .build();
    }
}
