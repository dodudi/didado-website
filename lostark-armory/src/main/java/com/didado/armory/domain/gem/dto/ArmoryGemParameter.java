package com.didado.armory.domain.gem.dto;

import com.didado.armory.domain.gem.domain.ArmoryGem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmoryGemParameter {
    @JsonProperty(value = "Gems")
    private List<GemParameter> gems;

    @JsonProperty(value = "Effects")
    private List<GemEffectParameter> effects;

    protected ArmoryGemParameter() {
    }

    public ArmoryGem toArmoryGem() {
        return new ArmoryGem();
    }
}
