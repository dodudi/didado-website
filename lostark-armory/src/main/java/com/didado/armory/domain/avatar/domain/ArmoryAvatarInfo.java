package com.didado.armory.domain.avatar.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ArmoryAvatarInfo {
    @Id
    @GeneratedValue
    @Column(name = "armory_avatar_info_id")
    private Long id;

    private String characterName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryAvatarInfo")
    private List<ArmoryAvatar> armoryAvatars = new ArrayList<>();

    protected ArmoryAvatarInfo() {
    }

    @Builder
    public ArmoryAvatarInfo(String characterName) {
        this.characterName = characterName;
    }
}
