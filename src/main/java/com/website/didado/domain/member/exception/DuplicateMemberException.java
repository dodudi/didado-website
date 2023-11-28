package com.website.didado.domain.member.exception;

import com.website.didado.domain.member.domain.MemberCode;
import lombok.Getter;

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
