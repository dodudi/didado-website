package com.website.didado.domain.member.exception;

import com.website.didado.domain.member.domain.MemberCode;

public class NotFoundMemberException extends RuntimeException{
    private final String message;
    private final int status;
    private Object data;

    public NotFoundMemberException() {
        this.message = MemberCode.NOT_FOUND_MEMBER.getMessage();
        this.status = MemberCode.NOT_FOUND_MEMBER.getStatus();
    }

    public NotFoundMemberException(Object data) {
        this();
        this.data = data;
    }
}
