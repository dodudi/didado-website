package com.didado.armory.domain.profile.repository;


import com.didado.armory.domain.profile.domain.Stat;
import com.didado.armory.domain.profile.domain.Tendency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArmoryTendencyRepository extends JpaRepository<Tendency, Long> {
    @Query("select t from Tendency t where t.armoryProfile.id = :profileId")
    List<Tendency> findTendencyByProfileId(@Param("profileId") Long profileId);
}
