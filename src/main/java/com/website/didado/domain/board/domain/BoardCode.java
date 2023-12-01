package com.website.didado.domain.board.domain;

import lombok.Getter;

@Getter
public enum BoardCode {
    NOT_FOUND_BOARD(404, "존재하지 않는 게시판입니다.");

    private final int status;
    private final String message;

    BoardCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}