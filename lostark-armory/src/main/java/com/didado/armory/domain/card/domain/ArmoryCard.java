package com.didado.armory.domain.card.domain;

import com.didado.armory.domain.card.dto.CardEffectParameter;
import com.didado.armory.domain.card.dto.CardParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ArmoryCard {
    @Id
    @GeneratedValue
    @Column(name = "armory_card_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryCard")
    private List<Card> cards = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryCard")
    private List<CardEffect> effects = new ArrayList<>();
}
