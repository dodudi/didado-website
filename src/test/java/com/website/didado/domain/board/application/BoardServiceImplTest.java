package com.website.didado.domain.board.application;

import com.website.didado.domain.board.domain.Board;
import com.website.didado.domain.board.dto.BoardParameter;
import com.website.didado.domain.board.dto.BoardResponse;
import com.website.didado.domain.board.repository.BoardRepository;
import com.website.didado.domain.member.domain.Member;
import com.website.didado.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@Slf4j
@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {
    @Mock
    private BoardRepository boardRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private BoardServiceImpl boardService;

    @Nested
    @DisplayName("게시판 생성 테스트")
    class Create {
        @Test
        void success() {
            //given
            Member member = new Member(1L, "username", "email@naver.com", "password");
            Board board = new Board(1L, "title", "content", member);
            BoardResponse boardResponse = new BoardResponse("게시판 생성에 성공했습니다.", 200, 1L);

            //when
            BDDMockito.given(memberRepository.findById(1L)).willReturn(Optional.of(member));
            BDDMockito.given(boardRepository.save(any())).willReturn(board);

            //then
            BoardParameter boardParameter = new BoardParameter("title", "content");
            BoardResponse result = boardService.create(boardParameter);

            Assertions.assertThat(result).isEqualTo(boardResponse);
        }
    }
}