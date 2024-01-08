package com.didado.armory.domain.avatar.repository;

import com.didado.armory.domain.avatar.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    List<Avatar> findByAvatarDataId(Long infoId);
}
