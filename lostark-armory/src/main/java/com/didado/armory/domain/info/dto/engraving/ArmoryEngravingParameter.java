package com.didado.armory.domain.info.dto.engraving;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmoryEngravingParameter {
    @JsonProperty(value = "Engravings")
    private List<EngravingParameter> engravings;

    @JsonProperty(value = "Effects")
    private List<EngravingEffectParameter> effects;
}
