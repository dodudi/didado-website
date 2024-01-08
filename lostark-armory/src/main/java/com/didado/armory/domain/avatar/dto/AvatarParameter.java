package com.didado.armory.domain.avatar.dto;

import com.didado.armory.domain.avatar.domain.Avatar;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AvatarParameter {
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

    protected AvatarParameter() {
    }

    public AvatarParameter(Avatar avatar) {
        this.type = avatar.getType();
        this.name = avatar.getName();
        this.icon = avatar.getIcon();
        this.grade = avatar.getGrade();
        this.isSet = avatar.getIsSet();
        this.isInner = avatar.getIsInner();
        this.toolTip = avatar.getToolTip();
    }

    public Avatar toArmoryAvatar() {
        return Avatar.builder()
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
