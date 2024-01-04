package com.didado.armory.domain.collectible.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class CollectibleInfo {
    @Id
    @GeneratedValue
    @Column(name = "collectible_info_id")
    private Long id;

    private String characterName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "collectibleInfo")
    private List<Collectible> collectibles = new ArrayList<>();

    protected CollectibleInfo() {
    }

    public CollectibleInfo(String characterName) {
        this.characterName = characterName;
    }
}
