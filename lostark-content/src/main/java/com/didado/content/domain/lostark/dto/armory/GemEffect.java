package com.didado.content.domain.lostark.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GemEffect {
    @JsonProperty(value = "GemSlot")
    private String gemSlot;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Description")
    private String description;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Tooltip")
    private String toolTip;
}
