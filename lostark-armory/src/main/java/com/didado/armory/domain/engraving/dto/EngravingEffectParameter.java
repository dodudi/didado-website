package com.didado.armory.domain.engraving.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EngravingEffectParameter {

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Description")
    private String description;
}
