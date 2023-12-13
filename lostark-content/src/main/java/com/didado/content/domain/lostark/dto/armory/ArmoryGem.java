package com.didado.content.domain.lostark.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmoryGem {
    @JsonProperty(value = "Gems")
    private List<Gem> gems;

    @JsonProperty(value = "Effects")
    private List<GemEffect> effects;
}
