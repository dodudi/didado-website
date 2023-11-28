package com.website.didado.domain.member.exception;

import lombok.Getter;

@Getter
public enum MemberCode {
    DUPLICATE_MEMBER(404, "중복 회원 에러입니다.");

    private final int status;
    private final String message;

    MemberCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
