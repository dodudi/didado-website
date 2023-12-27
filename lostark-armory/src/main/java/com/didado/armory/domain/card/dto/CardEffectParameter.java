package com.didado.armory.domain.card.dto;

import com.didado.armory.domain.card.domain.CardEffect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CardEffectParameter {
    @JsonProperty(value = "Index")
    private Integer index;

    @JsonProperty(value = "CardSlots")
    private List<Integer> cardSlots;

    @JsonProperty(value = "Items")
    private List<EffectParameter> items;

    public CardEffect toCardEffect() {
        return new CardEffect(index, cardSlots);
    }
}
