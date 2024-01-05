package com.didado.armory.domain.avatar.dto;

import com.didado.armory.domain.avatar.domain.ArmoryAvatarInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class AvatarInfoParameter {
    private String characterName;
    private List<ArmoryAvatarParameter> armoryAvatarParameters;

    protected AvatarInfoParameter() {
    }

    public AvatarInfoParameter(String characterName, List<ArmoryAvatarParameter> armoryAvatarParameters) {
        this.characterName = characterName;
        this.armoryAvatarParameters = armoryAvatarParameters;
    }
}
