package com.didado.armory.domain.core.dto;

import com.didado.armory.domain.core.domain.ArmoryType;
import lombok.Getter;

@Getter
public class CoreSaveParameter {
    private ArmoryType armoryType;
    private String characterName;

    protected CoreSaveParameter() {
    }

    public CoreSaveParameter(ArmoryType armoryType, String characterName) {
        this.armoryType = armoryType;
        this.characterName = characterName;
    }
}
