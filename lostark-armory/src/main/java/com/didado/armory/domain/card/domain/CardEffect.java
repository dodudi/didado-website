package com.didado.armory.domain.card.domain;

import com.didado.armory.domain.card.dto.EffectParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class CardEffect {
    @Id
    @GeneratedValue
    @Column(name = "card_effect_id")
    private Long id;
    private Integer index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_card_id")
    private ArmoryCard armoryCard;

    @ElementCollection
    private List<Integer> cardSlots;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cardEffect")
    private List<Effect> items = new ArrayList<>();

    public CardEffect(Integer index, List<Integer> cardSlots) {
        this.index = index;
        this.cardSlots = cardSlots;
    }

    public void changeArmoryCard(ArmoryCard armoryCard) {
        this.armoryCard = armoryCard;
        armoryCard.getEffects().add(this);
    }
}
