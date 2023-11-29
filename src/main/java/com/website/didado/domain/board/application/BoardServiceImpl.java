package com.website.didado.domain.board.application;

import com.website.didado.domain.board.domain.Board;
import com.website.didado.domain.board.dto.BoardParameter;
import com.website.didado.domain.board.dto.BoardResponse;
import com.website.didado.domain.board.repository.BoardRepository;
import com.website.didado.domain.member.domain.Member;
import com.website.didado.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public BoardResponse create(BoardParameter boardParameter) {
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("로그인 해주세요."));

        Board board = boardRepository.save(boardParameter.toBoard(member));
        log.debug("Create Board={}", board);
        return new BoardResponse("게시판 생성에 성공했습니다.", 200, board.getId());
    }

    @Override
    public BoardResponse search(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 게시판 id 입니다."));

        log.debug("Search Board={}", board);
        return new BoardResponse("게시판 조회에 성공했습니다.", 200, board);
    }
}
