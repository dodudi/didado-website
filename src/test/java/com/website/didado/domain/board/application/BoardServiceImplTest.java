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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
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
            Board board = new Board(1L, "title", "content", member);
            given(boardRepository.findByIdFetch(any())).willReturn(Optional.of(board));
            //when
            BoardResponse result = boardService.search(1L);

            //then
            BoardResponse response = new BoardResponse("게시판 조회에 성공했습니다.", 200, board);
            assertThat(result).isEqualTo(response);
        }

        @Test
        @DisplayName("게시판 페이징 조회 - 성공 테스트")
        void successPage() {
            //given
            Member member = new Member(1L, "username", "email@naver.com", "password");
            List<Board> boards = List.of(
                    new Board(1L, "title1", "content1", member),
                    new Board(2L, "title2", "content2", member),
                    new Board(3L, "title3", "content3", member),
                    new Board(4L, "title4", "content4", member),
                    new Board(5L, "title5", "content5", member)
            );

            PageRequest pageRequest = PageRequest.of(1, 2);
            List<Board> list = boards.stream()
                    .skip(pageRequest.getOffset())
                    .limit(pageRequest.getPageSize())
                    .toList();

            Page<Board> page = new PageImpl<>(list, pageRequest, list.size());
//            for (Board board : page)
//                log.info("Board Id={}", board.getId());

            given(boardRepository.findPage(any())).willReturn(page);

            //when
            BoardResponse result = boardService.search(pageRequest);

            //then
            BoardResponse response = new BoardResponse("게시판 조회에 성공했습니다.", 200, page);
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

    @Nested
    @DisplayName("게시판 업데이트 테스트")
    class Update {
        @Test
        @DisplayName("게시판 업데이트 - 성공 테스트")
        void success() {
            //given
            Member member = new Member(1L, "username", "email@naver.com", "password");
            Board board = new Board(1L, "title", "content", member);
            given(boardRepository.findByIdFetch(any())).willReturn(Optional.of(board));

            //when
            BoardResponse result = boardService.update(1L, new BoardParameter("updateTitle", "updateContent"));

            //then
            assertThat(result).isEqualTo(new BoardResponse("게시판 업데이트에 성공했습니다.", 200, board));
        }

        @Test
        @DisplayName("존재 하지 않는 게시판 ID 에러 발생 - 실패 테스트")
        void throwIllegalStateException() {
            //given
            given(boardRepository.findByIdFetch(any())).willReturn(Optional.empty());
            assertThatThrownBy(() -> boardService.update(1L, new BoardParameter("updateTitle", "updateContent"))).isInstanceOf(IllegalStateException.class);
        }
    }
}