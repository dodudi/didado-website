package com.website.didado.domain.board.api;

import com.website.didado.domain.board.application.BoardService;
import com.website.didado.domain.board.dto.BoardParameter;
import com.website.didado.domain.board.dto.BoardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<BoardResponse> create(@Valid @RequestBody BoardParameter boardParameter) {
        return ResponseEntity.ok(boardService.create(boardParameter));
    }

    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponse> search(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.search(id));
    }

    @GetMapping("/boards")
    public ResponseEntity<BoardResponse> searchPage(Pageable pageable) {
        return ResponseEntity.ok(boardService.search(pageable));
    }

    @PostMapping("/boards/{id}/delete")
    public ResponseEntity<BoardResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.delete(id));
    }

    @PostMapping("/boards/{id}/update")
    public ResponseEntity<BoardResponse> update(@PathVariable Long id, @RequestBody BoardParameter boardParameter) {
        return ResponseEntity.ok(boardService.update(id, boardParameter));
    }
}
