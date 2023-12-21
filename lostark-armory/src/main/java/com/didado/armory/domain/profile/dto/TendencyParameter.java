package com.didado.armory.domain.profile.dto;

import com.didado.armory.domain.profile.domain.Tendency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TendencyParameter {
    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "Point")
    private Integer point;

    @JsonProperty(value = "MaxPoint")
    private Integer maxPoint;

    public Tendency toTendency() {
        return Tendency.builder()
                .type(type)
                .point(point)
                .maxPoint(maxPoint)
                .build();
    }
}
