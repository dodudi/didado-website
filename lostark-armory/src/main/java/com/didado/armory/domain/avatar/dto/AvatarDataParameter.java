package com.didado.armory.domain.avatar.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class AvatarDataParameter {
    private String characterName;
    private List<AvatarParameter> avatarParameters;

    protected AvatarDataParameter() {
    }

    public AvatarDataParameter(String characterName, List<AvatarParameter> avatarParameters) {
        this.characterName = characterName;
        this.avatarParameters = avatarParameters;
    }
}
