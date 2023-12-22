package com.didado.armory.domain.avatar.domain;

import com.didado.armory.domain.avatar.dto.ArmoryAvatarParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class ArmoryAvatar {
    @Id
    @GeneratedValue
    @Column(name = "armory_avatar_id")
    private Long id;

    private String characterName;
    private String type;
    private String name;
    private String icon;
    private String grade;
    private Boolean isSet;
    private Boolean isInner;
    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

    protected ArmoryAvatar() {
    }

    @Builder
    public ArmoryAvatar(String characterName, String type, String name, String icon, String grade, Boolean isSet, Boolean isInner, String toolTip) {
        this.characterName = characterName;
        this.type = type;
        this.name = name;
        this.icon = icon;
        this.grade = grade;
        this.isSet = isSet;
        this.isInner = isInner;
        this.toolTip = toolTip;
    }

    public ArmoryAvatar updateData(ArmoryAvatarParameter parameter, String characterName) {
        this.characterName = characterName;
        this.type = parameter.getType();
        this.name = parameter.getName();
        this.icon = parameter.getIcon();
        this.grade = parameter.getGrade();
        this.isSet = parameter.getIsSet();
        this.isInner = parameter.getIsInner();
        this.toolTip = parameter.getToolTip();
        return this;
    }
}
