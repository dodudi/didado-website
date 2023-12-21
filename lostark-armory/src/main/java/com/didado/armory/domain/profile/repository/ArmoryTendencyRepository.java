package com.didado.armory.domain.profile.repository;


import com.didado.armory.domain.profile.domain.Tendency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmoryTendencyRepository extends JpaRepository<Tendency, Long> {
}
