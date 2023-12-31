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
    private List<Integer> cardSlots = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cardEffect", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Effect> items = new ArrayList<>();

    protected CardEffect() {
    }

    public CardEffect(Integer index, List<Integer> cardSlots) {
        this.index = index;
        this.cardSlots = cardSlots;
    }

    public void changeData(CardEffect cardEffect) {
        this.index = cardEffect.getIndex();
        this.cardSlots = cardEffect.getCardSlots();
    }

    public void changeArmoryCard(ArmoryCard armoryCard) {
        this.armoryCard = armoryCard;
        armoryCard.getEffects().add(this);
    }

    public void deleteArmoryCard(ArmoryCard armoryCard) {
        armoryCard.getEffects().remove(this);
        this.armoryCard = null;
    }
}
