package com.didado.armory.domain.info.dto.collectible;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CollectibleParameter {
    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Point")
    private Integer point;

    @JsonProperty(value = "MaxPoint")
    private Integer maxPoint;

    @JsonProperty(value = "CollectiblePoints")
    private List<CollectiblePointParameter> collectiblePoints;
}
