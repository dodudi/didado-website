package com.didado.armory.domain.avatar.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
public class AvatarData {
    @Id
    @GeneratedValue
    @Column(name = "avatar_data_id")
    private Long id;

    private String characterName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "avatarData")
    private List<Avatar> avatars = new ArrayList<>();

    protected AvatarData() {
    }

    public AvatarData(String characterName) {
        this.characterName = characterName;
    }
}
