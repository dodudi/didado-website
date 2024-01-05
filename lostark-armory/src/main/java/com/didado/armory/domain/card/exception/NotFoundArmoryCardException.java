package com.didado.armory.domain.card.exception;

import lombok.Getter;

@Getter
public class NotFoundArmoryCardException extends RuntimeException {
    private final String characterName;

    public NotFoundArmoryCardException(String message, String characterName) {
        super(message);
        this.characterName = characterName;
    }
}
