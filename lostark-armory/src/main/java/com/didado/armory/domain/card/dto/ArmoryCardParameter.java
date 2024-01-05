package com.didado.armory.domain.card.dto;

import com.didado.armory.domain.card.domain.ArmoryCard;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ArmoryCardParameter {
    @JsonProperty(value = "Cards")
    private List<CardParameter> cards = new ArrayList<>();

    @JsonProperty(value = "Effects")
    private List<CardEffectParameter> effects = new ArrayList<>();


    public ArmoryCard toArmoryCard(String characterName) {
        return new ArmoryCard(characterName);
    }
}
