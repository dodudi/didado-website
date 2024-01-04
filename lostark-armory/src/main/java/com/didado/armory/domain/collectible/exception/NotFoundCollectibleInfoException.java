package com.didado.armory.domain.collectible.exception;

import lombok.Getter;

@Getter
public class NotFoundCollectibleInfoException extends RuntimeException {
    private final String characterName;

    public NotFoundCollectibleInfoException(String message, String characterName) {
        super(message);
        this.characterName = characterName;
    }
}
