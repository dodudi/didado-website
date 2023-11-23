package com.website.didado.domain.member.dto;

import com.website.didado.domain.member.domain.Member;
import jakarta.validation.constraints.NotEmpty;

public record MemberParameter(
        @NotEmpty String username,
        @NotEmpty String firstEmail,
        @NotEmpty String lastEmail,
        @NotEmpty String password) {
    public Member toMember() {
        return new Member(username, fullEmail(), password);
    }

    public String fullEmail() {
        return firstEmail + "@" + lastEmail;
    }
}
