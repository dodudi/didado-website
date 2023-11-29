package com.website.didado.domain.board.api;

import com.website.didado.domain.board.application.BoardService;
import com.website.didado.domain.board.dto.BoardParameter;
import com.website.didado.domain.board.dto.BoardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<BoardResponse> search(@Valid @RequestBody BoardParameter boardParameter) {
        return ResponseEntity.ok(boardService.create(boardParameter));
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponse> create(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.search(id));
    }


}
