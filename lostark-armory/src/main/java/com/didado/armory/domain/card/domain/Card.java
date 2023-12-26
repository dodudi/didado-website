package com.didado.armory.domain.card.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Card {
    @Id
    @GeneratedValue
    @Column(name = "card_id")
    private Long id;

    private String slot;

    private String name;

    private String icon;

    private String awakeCount;

    private String awakeTotal;

    private String grade;

    @Column(name = "tool_tip", length = 10000)
    private String toolTip;
}
