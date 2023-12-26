package com.didado.armory.domain.info.repository;

import com.didado.armory.domain.info.domain.Armory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmoryRepository extends JpaRepository<Armory, Long> {
}
