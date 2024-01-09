package com.didado.armory.domain.card.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArmoryCard {
    @Id
    @GeneratedValue
    @Column(name = "armory_card_id")
    private Long id;

    private String characterName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryCard", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryCard", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CardEffect> effects = new ArrayList<>();

    @Builder
    public ArmoryCard(String characterName) {
        this.characterName = characterName;
    }
}
