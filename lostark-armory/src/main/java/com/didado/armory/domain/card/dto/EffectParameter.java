package com.didado.armory.domain.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EffectParameter {
    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Description")
    private String description;
}
