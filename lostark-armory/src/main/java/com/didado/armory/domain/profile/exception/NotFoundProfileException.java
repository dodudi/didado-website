package com.didado.armory.domain.profile.exception;

import lombok.Getter;

@Getter
public class NotFoundProfileException extends RuntimeException {

    private final String characterName;

    public NotFoundProfileException(String message, String characterName) {
        super(message);
        this.characterName = characterName;
    }
}
