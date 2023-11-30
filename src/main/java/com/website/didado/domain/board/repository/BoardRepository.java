package com.website.didado.domain.board.repository;

import com.website.didado.domain.board.domain.Board;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from  Board b left join fetch b.member where  b.id=:id")
    Optional<Board> findByIdFetch(@Param("id") Long id);
}
