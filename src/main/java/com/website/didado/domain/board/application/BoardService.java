package com.website.didado.domain.board.application;

import com.website.didado.domain.board.dto.BoardParameter;
import com.website.didado.domain.board.dto.BoardResponse;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    BoardResponse create(BoardParameter boardParameter);

    BoardResponse search(Long id);

    BoardResponse search(Pageable pageable);

    BoardResponse delete(Long id);

    BoardResponse update(Long id, BoardParameter boardParameter);
}
