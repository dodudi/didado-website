package com.didado.armory.domain.engraving.dto;

import com.didado.armory.domain.engraving.domain.ArmoryEngraving;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ArmoryEngravingParameter {
    @JsonProperty(value = "Engravings")
    private List<EngravingParameter> engravings;

    @JsonProperty(value = "Effects")
    private List<EngravingEffectParameter> effects;

    public ArmoryEngraving toArmoryEngraving() {
        return new ArmoryEngraving();
    }
}
