package com.didado.armory.domain.profile.exception;

public class NotFoundProfileException extends RuntimeException {
    public NotFoundProfileException() {
    }

    public NotFoundProfileException(String message) {
        super(message);
    }

    public NotFoundProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundProfileException(Throwable cause) {
        super(cause);
    }

    public NotFoundProfileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
