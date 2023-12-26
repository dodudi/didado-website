package com.didado.armory.domain.info.dto.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EffectParameter {
    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Description")
    private String description;
}
