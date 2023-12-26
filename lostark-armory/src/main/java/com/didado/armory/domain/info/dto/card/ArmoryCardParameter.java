package com.didado.armory.domain.info.dto.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmoryCardParameter {
    @JsonProperty(value = "Cards")
    private List<CardParameter> cards;

    @JsonProperty(value = "Effects")
    private List<CardEffectParameter> effects;
}
