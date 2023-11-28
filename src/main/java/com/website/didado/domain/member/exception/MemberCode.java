package com.website.didado.domain.member.exception;

import lombok.Getter;

@Getter
public enum MemberCode {
    NOT_FOUND_MEMBER(404, "존재하지 않는 회원입니다."),
    DUPLICATE_MEMBER(404, "중복 회원 에러입니다.");

    private final int status;
    private final String message;

    MemberCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
