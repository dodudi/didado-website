package com.didado.armory.domain.avatar.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString(exclude = {"avatarData", "toolTip"})
public class Avatar {
    @Id
    @GeneratedValue
    @Column(name = "avatar_id")
    private Long id;
    private String type;
    private String name;
    private String icon;
    private String grade;
    private Boolean isSet;
    private Boolean isInner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_data_id")
    private AvatarData avatarData;

    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

    protected Avatar() {
    }

    @Builder
    public Avatar(String type, String name, String icon, String grade, Boolean isSet, Boolean isInner, String toolTip) {
        this.type = type;
        this.name = name;
        this.icon = icon;
        this.grade = grade;
        this.isSet = isSet;
        this.isInner = isInner;
        this.toolTip = toolTip;
    }

    public void changeAvatarData(AvatarData avatarData) {
        this.avatarData = avatarData;
        avatarData.getAvatars().add(this);
    }

    public void deleteAvatarData() {
        this.avatarData.getAvatars().remove(this);
        this.avatarData = null;
    }

    public void changeData(Avatar parameter) {
        this.type = parameter.getType();
        this.name = parameter.getName();
        this.icon = parameter.getIcon();
        this.grade = parameter.getGrade();
        this.isSet = parameter.getIsSet();
        this.isInner = parameter.getIsInner();
        this.toolTip = parameter.getToolTip();
    }

    public String getKey() {
        return type + isInner;
    }
}
