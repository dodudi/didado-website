package com.didado.armory.domain.avatar.dto;

import com.didado.armory.domain.avatar.domain.ArmoryAvatar;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ArmoryAvatarParameter {
    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Grade")
    private String grade;

    @JsonProperty(value = "IsSet")
    private Boolean isSet;

    @JsonProperty(value = "IsInner")
    private Boolean isInner;

    @JsonProperty(value = "Tooltip")
    private String toolTip;

    protected ArmoryAvatarParameter() {
    }

    public ArmoryAvatarParameter(ArmoryAvatar armoryAvatar) {
        this.type = armoryAvatar.getType();
        this.name = armoryAvatar.getName();
        this.icon = armoryAvatar.getIcon();
        this.grade = armoryAvatar.getGrade();
        this.isSet = armoryAvatar.getIsSet();
        this.isInner = armoryAvatar.getIsInner();
        this.toolTip = armoryAvatar.getToolTip();
    }

    public ArmoryAvatar toArmoryAvatar() {
        return ArmoryAvatar.builder()
                .type(type)
                .name(name)
                .icon(icon)
                .grade(grade)
                .isSet(isSet)
                .isInner(isInner)
                .toolTip(toolTip)
                .build();
    }
}
