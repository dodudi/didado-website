package com.didado.armory.domain.info.dto.engraving;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EngravingEffectParameter {

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Description")
    private String description;
}
