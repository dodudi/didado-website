package com.didado.armory.domain.engraving.dto;

import com.didado.armory.domain.engraving.domain.Engraving;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EngravingParameter {

    @JsonProperty(value = "Slot")
    private Integer slot;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Tooltip")
    private String toolTip;

    public Engraving toEngraving() {
        return Engraving.builder()
                .slot(slot)
                .name(name)
                .icon(icon)
                .toolTip(toolTip)
                .build();
    }
}
