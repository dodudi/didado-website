package com.didado.armory.domain.profile.exception;

public class AlreadyExistProfileException extends RuntimeException {
    public AlreadyExistProfileException() {
    }

    public AlreadyExistProfileException(String message) {
        super(message);
    }

    public AlreadyExistProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistProfileException(Throwable cause) {
        super(cause);
    }

    public AlreadyExistProfileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
