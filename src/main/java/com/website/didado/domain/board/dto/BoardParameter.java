package com.website.didado.domain.board.dto;

import com.website.didado.domain.board.domain.Board;
import com.website.didado.domain.member.domain.Member;
import jakarta.validation.constraints.NotEmpty;

public record BoardParameter(
        @NotEmpty(message = "게시판 제목을 입력해 주세요.") String title,
        @NotEmpty(message = "게시판 내용을 입력해 주세요.") String content) {
    public Board toBoard(Member member) {
        return new Board(title, content, member);
    }
}
