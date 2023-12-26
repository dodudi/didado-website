package com.didado.armory.domain.info.dto.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CardEffectParameter {
    @JsonProperty(value = "Index")
    private Integer index;

    @JsonProperty(value = "CardSlots")
    private List<Integer> cardSlots;

    @JsonProperty(value = "Items")
    private List<EffectParameter> items;
}
