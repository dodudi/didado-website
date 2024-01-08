package com.didado.armory.domain.avatar.dto;

import lombok.Getter;

@Getter
public class AvatarResponse {
    private int status;
    private String message;
    private Object data;

    public AvatarResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public AvatarResponse(int status, Object data) {
        this(status, "Default Message", data);
    }

    public AvatarResponse(int status) {
        this(status, "Default Message", null);
    }
}
