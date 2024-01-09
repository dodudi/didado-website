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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cardEffect")
    private List<Effect> items = new ArrayList<>();

    @Builder
    public CardEffect(Integer index) {
        this.index = index;
    }

    public void changeArmoryCard(ArmoryCard armoryCard) {
        this.armoryCard = armoryCard;
        this.armoryCard.getEffects().add(this);
    }
}
