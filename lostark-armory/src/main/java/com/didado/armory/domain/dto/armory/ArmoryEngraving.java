package com.didado.armory.domain.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmoryEngraving {
    @JsonProperty(value = "Engravings")
    private List<Engraving> engravings;

    @JsonProperty(value = "Effects")
    private List<EngravingEffect> effects;
}
