package com.didado.armory.domain.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ArmoryAvatar {
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
