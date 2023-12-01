package com.website.didado.domain.board.repository;

import com.website.didado.domain.board.domain.Board;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from  Board b left join fetch b.member where  b.id=:id")
    Optional<Board> findByIdFetch(@Param("id") Long id);

    @Query("select b from Board b left join fetch b.member")
    Page<Board> findPage(Pageable pageable);
}
