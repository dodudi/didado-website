package com.didado.armory.domain.info.dto.collectible;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CollectiblePointParameter {
    @JsonProperty(value = "PointName")
    private String pointName;

    @JsonProperty(value = "Point")
    private Integer point;

    @JsonProperty(value = "MaxPoint")
    private Integer maxPoint;
}
