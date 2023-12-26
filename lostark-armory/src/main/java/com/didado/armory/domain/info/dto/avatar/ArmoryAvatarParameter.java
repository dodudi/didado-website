package com.didado.armory.domain.info.dto.avatar;

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
}
