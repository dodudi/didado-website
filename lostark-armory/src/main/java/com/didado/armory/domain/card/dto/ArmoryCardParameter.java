package com.didado.armory.domain.card.dto;

import com.didado.armory.domain.card.domain.ArmoryCard;
import com.didado.armory.domain.card.domain.Card;
import com.didado.armory.domain.card.domain.Effect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArmoryCardParameter {
    @JsonProperty(value = "Cards")
    private List<CardParameter> cards = new ArrayList<>();

    @JsonProperty(value = "Effects")
    private List<CardEffectParameter> effects = new ArrayList<>();

    public ArmoryCard toArmoryCard(String characterName) {
        return ArmoryCard.builder()
                .characterName(characterName)
                .build();
    }
}
