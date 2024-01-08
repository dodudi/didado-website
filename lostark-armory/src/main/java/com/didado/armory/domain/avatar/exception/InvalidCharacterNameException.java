package com.didado.armory.domain.avatar.exception;

import lombok.Getter;

@Getter
public class InvalidCharacterNameException extends RuntimeException {
    private final String characterName;

    public InvalidCharacterNameException(String message, String characterName) {
        super(message);
        this.characterName = characterName;
    }
}
