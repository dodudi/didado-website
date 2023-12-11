package com.website.didado.domain.lostark.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CardEffect {
    @JsonProperty(value = "Index")
    private Integer index;

    @JsonProperty(value = "CardSlots")
    private List<Integer> cardSlots;

    @JsonProperty(value = "Items")
    private List<Effect> items;
}
