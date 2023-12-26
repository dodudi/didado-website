package com.didado.armory.domain.info.dto.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CardParameter {
    @JsonProperty(value = "Slot")
    private String slot;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "AwakeCount")
    private String awakeCount;

    @JsonProperty(value = "AwakeTotal")
    private String awakeTotal;

    @JsonProperty(value = "Grade")
    private String grade;

    @JsonProperty(value = "Tooltip")
    private String toolTip;
}
