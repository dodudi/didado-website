package com.didado.armory.domain.card.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Effect {
    @Id
    @GeneratedValue
    @Column(name = "effect_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_effect_id")
    private CardEffect cardEffect;

    private String name;
    private String description;

    @Builder
    public Effect(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void changeCardEffect(CardEffect cardEffect) {
        this.cardEffect = cardEffect;
        this.cardEffect.getItems().add(this);
    }
}
