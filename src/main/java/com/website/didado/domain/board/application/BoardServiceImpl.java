package com.website.didado.domain.board.application;

import com.website.didado.domain.board.domain.Board;
import com.website.didado.domain.board.dto.BoardParameter;
import com.website.didado.domain.board.dto.BoardResponse;
import com.website.didado.domain.board.exception.NotFoundBoardException;
import com.website.didado.domain.board.repository.BoardRepository;
import com.website.didado.domain.member.domain.Member;
import com.website.didado.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Board board = boardRepository.findByIdFetch(id)
                .orElseThrow(NotFoundBoardException::new);

        return new BoardResponse("게시판 조회에 성공했습니다.", 200, board);
    }

    @Override
    public BoardResponse search(Pageable pageable) {
        PageRequest pageRequest = (PageRequest) pageable;
        log.debug("Page={}", pageRequest.getPageNumber());
        log.debug("Page Size={}", pageRequest.getPageSize());
        log.debug("Page Offset={}", pageRequest.getOffset());

        Page<Board> boards = boardRepository.findPage(pageRequest);
        return new BoardResponse("게시판 조회에 성공했습니다.", 200, boards);
    }

    @Override
    public BoardResponse delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(NotFoundBoardException::new);

        boardRepository.delete(board);
        return new BoardResponse("게시판 삭제에 성공했습니다.", 200, id);
    }

    @Override
    @Transactional
    public BoardResponse update(Long id, BoardParameter boardParameter) {
        Board board = boardRepository.findByIdFetch(id)
                .orElseThrow(NotFoundBoardException::new);

        board.updateBoard(boardParameter);
        return new BoardResponse("게시판 업데이트에 성공했습니다.", 200, board);
    }


}
