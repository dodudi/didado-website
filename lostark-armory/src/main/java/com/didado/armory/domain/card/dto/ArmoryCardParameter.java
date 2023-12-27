package com.didado.armory.domain.card.dto;

import com.didado.armory.domain.card.domain.ArmoryCard;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmoryCardParameter {
    @JsonProperty(value = "Cards")
    private List<CardParameter> cards;

    @JsonProperty(value = "Effects")
    private List<CardEffectParameter> effects;

    public ArmoryCard toArmoryCard() {
        return new ArmoryCard();
    }
}
