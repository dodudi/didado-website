package com.didado.armory.domain.colosseum.exception;

import lombok.Getter;

@Getter
public class NotFoundColosseumInfoException extends RuntimeException {
    private final String characterName;

    public NotFoundColosseumInfoException(String message, String characterName) {
        super(message);
        this.characterName = characterName;
    }
}
