package com.website.didado.domain.member.dto;

import com.website.didado.domain.member.domain.Member;
import jakarta.validation.constraints.NotEmpty;

public record MemberParameter(
        @NotEmpty(message = "회원 이름을 입력해 주세요.") String username,
        @NotEmpty(message = "이메일을 입력해 주세요.") String firstEmail,
        @NotEmpty(message = "이메일을 입력해 주세요.") String lastEmail,
        @NotEmpty(message = "비밀번호를 입력해 주세요.") String password) {
    public Member toMember() {
        return new Member(username, fullEmail(), password);
    }

    public String fullEmail() {
        return firstEmail + "@" + lastEmail;
    }
}
