package com.didado.armory.domain.card.dto;

import com.didado.armory.domain.card.domain.Card;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CardParameter {
    @JsonProperty(value = "Slot")
    private String slot;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "AwakeCount")
    private String awakeCount;

    @JsonProperty(value = "AwakeTotal")
    private String awakeTotal;

    @JsonProperty(value = "Grade")
    private String grade;

    @JsonProperty(value = "Tooltip")
    private String toolTip;

    public Card toCard() {
        return Card.builder()
                .slot(slot)
                .name(name)
                .icon(icon)
                .awakeCount(awakeCount)
                .awakeTotal(awakeTotal)
                .grade(grade)
                .toolTip(toolTip)
                .build();
    }
}
