package com.didado.armory.domain.collectible.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CollectiblePointParameter {
    @JsonProperty(value = "PointName")
    private String pointName;

    @JsonProperty(value = "Point")
    private Integer point;

    @JsonProperty(value = "MaxPoint")
    private Integer maxPoint;
}
