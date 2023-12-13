package com.didado.content.domain.lostark.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmoryCard {
    @JsonProperty(value = "Cards")
    private List<Card> cards;

    @JsonProperty(value = "Effects")
    private List<CardEffect> effects;
}
