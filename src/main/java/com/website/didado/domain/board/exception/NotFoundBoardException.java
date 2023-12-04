package com.website.didado.domain.board.exception;

import com.website.didado.domain.board.domain.BoardCode;
import com.website.didado.domain.member.domain.MemberCode;
import lombok.Getter;

@Getter
public class NotFoundBoardException extends RuntimeException {
    private final String message;
    private final int status;
    private Object data;

    public NotFoundBoardException() {
        this.message = BoardCode.NOT_FOUND_BOARD.getMessage();
        this.status = BoardCode.NOT_FOUND_BOARD.getStatus();
    }

    public NotFoundBoardException(Object data) {
        this();
        this.data = data;
    }
}
