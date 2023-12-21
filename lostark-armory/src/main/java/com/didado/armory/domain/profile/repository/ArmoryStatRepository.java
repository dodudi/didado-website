package com.didado.armory.domain.profile.repository;

import com.didado.armory.domain.profile.domain.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArmoryStatRepository extends JpaRepository<Stat, Long> {
    @Query("select s from armory_stat s join fetch s.armoryProfile where s.armoryProfile.id = :profileId")
    List<Stat> findByArmoryProfile(@Param("profileId") Long profileId);
}
