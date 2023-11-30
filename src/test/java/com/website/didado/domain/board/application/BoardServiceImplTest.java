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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

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
        @DisplayName("게시판 생성 - 성공 테스트")
        void success() {
            //given
            Member member = new Member(1L, "username", "email@naver.com", "password");
            Board board = new Board(1L, "title", "content", member);
            BoardResponse boardResponse = new BoardResponse("게시판 생성에 성공했습니다.", 200, 1L);

            //when
            given(memberRepository.findById(1L)).willReturn(Optional.of(member));
            given(boardRepository.save(any())).willReturn(board);

            //then
            BoardParameter boardParameter = new BoardParameter("title", "content");
            BoardResponse result = boardService.create(boardParameter);

            assertThat(result).isEqualTo(boardResponse);
        }

        @Test
        @DisplayName("로그인 하지 않은 에러 - 실패 테스트")
        void throwNotLoginException() {
            //when
            given(memberRepository.findById(1L)).willReturn(Optional.empty());

            //then
            BoardParameter boardParameter = new BoardParameter("title", "content");
            assertThatThrownBy(() -> boardService.create(boardParameter))
                    .isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    @DisplayName("게시판 조회 테스트")
    class Search {
        @Test
        @DisplayName("게시판 단일 조회 - 성공 테스트")
        void success() {
            //given
            Member member = new Member(1L, "username", "email@naver.com", "password");
            Board board = new Board("title", "content", member);
            given(boardRepository.findById(any())).willReturn(Optional.of(board));
            //when
            BoardResponse result = boardService.search(1L);

            //then
            BoardResponse response = new BoardResponse("게시판 조회에 성공했습니다.", 200, board);
            assertThat(result).isEqualTo(response);
        }

        @Test
        @DisplayName("존재 하지 않는 게시판 id 에러 - 실패 테스트")
        void throwNotFoundBoardException() {
            //given
            given(boardRepository.findById(any())).willReturn(Optional.empty());

            //when
            assertThatThrownBy(() -> boardService.search(1L))
                    .isInstanceOf(IllegalStateException.class);
        }


    }

    @Nested
    @DisplayName("게시판 삭제 테스트")
    class Delete {
        @Test
        @DisplayName("게시판 삭제 - 성공 테스트")
        void success() {
            //given
            Member member = new Member(1L, "username", "email@naver.com", "password");
            Board board = new Board(1L, "title", "content", member);
            given(boardRepository.findById(any())).willReturn(Optional.of(board));

            //when
            BoardResponse result = boardService.delete(1L);

            //then
            BoardResponse response = new BoardResponse("게시판 삭제에 성공했습니다.", 200, 1L);
            assertThat(result).isEqualTo(response);
        }

        @Test
        @DisplayName("존재 하지 않는 게시판 ID 에러 발생 - 실패 테스트")
        void throwIllegalStateException() {
            //given
            given(boardRepository.findById(any())).willReturn(Optional.empty());

            //when
            assertThatThrownBy(() -> boardService.delete(1L)).isInstanceOf(IllegalStateException.class);
        }
    }
}