package com.website.didado.domain.member.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DuplicateMemberException extends RuntimeException {
    private final String message;
    private final int status;
    private Object data;

    public DuplicateMemberException() {
        this.message = MemberCode.DUPLICATE_MEMBER.getMessage();
        this.status = MemberCode.DUPLICATE_MEMBER.getStatus();
    }

    public DuplicateMemberException(Object data) {
        this();
        this.data = data;
    }
}
