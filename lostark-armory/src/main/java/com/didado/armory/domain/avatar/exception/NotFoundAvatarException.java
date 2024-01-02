package com.didado.armory.domain.avatar.exception;

import lombok.Getter;

@Getter
public class NotFoundAvatarException extends RuntimeException {
    private final String characterName;

    public NotFoundAvatarException(String message, String characterName) {
        super(message);
        this.characterName = characterName;
    }

}
