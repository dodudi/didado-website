package com.didado.armory.domain.card.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
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

    protected Effect() {
    }

    @Builder
    public Effect(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void changeCardEffect(CardEffect cardEffect) {
        this.cardEffect = cardEffect;
        cardEffect.getItems().add(this);
    }

    public void deleteCardEffect(CardEffect cardEffect) {
        this.cardEffect = null;
        cardEffect.getItems().remove(this);
    }
}
