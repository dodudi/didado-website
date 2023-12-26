package com.didado.armory.domain.info.dto.engraving;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EngravingParameter {

    @JsonProperty(value = "Slot")
    private Integer slot;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Tooltip")
    private String toolTip;

}
