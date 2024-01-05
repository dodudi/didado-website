package com.didado.armory.domain.gem.dto;

import com.didado.armory.domain.gem.domain.GemEffect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GemEffectParameter {
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

    public GemEffect toGemEffect() {
        return GemEffect.builder()
                .gemSlot(gemSlot)
                .name(name)
                .description(description)
                .icon(icon)
                .toolTip(toolTip)
                .build();
    }
}
