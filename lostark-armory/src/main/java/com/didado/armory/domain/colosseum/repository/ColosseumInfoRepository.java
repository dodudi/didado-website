package com.didado.armory.domain.colosseum.repository;

import com.didado.armory.domain.colosseum.domain.ColosseumInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColosseumInfoRepository extends JpaRepository<ColosseumInfo, Long> {
}
