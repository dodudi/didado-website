package com.didado.armory.domain.card.domain;

import com.didado.armory.domain.card.dto.EffectParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class CardEffect {
    @Id
    @GeneratedValue
    @Column(name = "card_effect_id")
    private Long id;
    private Integer index;

    @ElementCollection
    private List<Integer> cardSlots;

    @OneToMany
    private List<Effect> items;
}
