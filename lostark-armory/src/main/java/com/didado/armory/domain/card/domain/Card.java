package com.didado.armory.domain.card.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card {
    @Id
    @GeneratedValue
    @Column(name = "card_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_card_id")
    private ArmoryCard armoryCard;

    private String slot;

    private String name;

    private String icon;

    private String awakeCount;

    private String awakeTotal;

    private String grade;

    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

    @Builder
    public Card(String slot, String name, String icon, String awakeCount, String awakeTotal, String grade, String toolTip) {
        this.slot = slot;
        this.name = name;
        this.icon = icon;
        this.awakeCount = awakeCount;
        this.awakeTotal = awakeTotal;
        this.grade = grade;
        this.toolTip = toolTip;
    }

    public void changeArmoryCard(ArmoryCard armoryCard) {
        this.armoryCard = armoryCard;
        this.armoryCard.getCards().add(this);
    }
}
