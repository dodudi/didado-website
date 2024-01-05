package com.didado.armory.domain.avatar.repository;

import com.didado.armory.domain.avatar.domain.ArmoryAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvatarRepository extends JpaRepository<ArmoryAvatar, Long> {
    List<ArmoryAvatar> findByArmoryAvatarInfoId(Long infoId);
}
