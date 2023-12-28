package com.didado.armory.domain.colosseum.repository;

import com.didado.armory.domain.colosseum.domain.Colosseum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColosseumRepository extends JpaRepository<Colosseum, Long> {
}
