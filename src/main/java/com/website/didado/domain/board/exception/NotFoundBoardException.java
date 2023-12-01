package com.website.didado.domain.board.exception;

import com.website.didado.domain.member.domain.MemberCode;
import lombok.Getter;

@Getter
public class NotFoundBoardException extends RuntimeException{
    private final String message;
    private final int status;
    private Object data;

    public NotFoundBoardException() {
        this.message = MemberCode.DUPLICATE_MEMBER.getMessage();
        this.status = MemberCode.DUPLICATE_MEMBER.getStatus();
    }

    public NotFoundBoardException(Object data) {
        this();
        this.data = data;
    }
}
