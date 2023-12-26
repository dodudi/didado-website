package com.didado.armory.domain.info.dto.gem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmoryGemParameter {
    @JsonProperty(value = "Gems")
    private List<GemParameter> gems;

    @JsonProperty(value = "Effects")
    private List<GemEffectParameter> effects;
}
