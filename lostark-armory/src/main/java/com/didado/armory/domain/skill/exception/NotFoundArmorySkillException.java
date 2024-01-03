package com.didado.armory.domain.skill.exception;

import lombok.Getter;

@Getter
public class NotFoundArmorySkillException extends RuntimeException {
    private final String characterName;

    public NotFoundArmorySkillException(String message, String characterName) {
        super(message);
        this.characterName = characterName;
    }
}
