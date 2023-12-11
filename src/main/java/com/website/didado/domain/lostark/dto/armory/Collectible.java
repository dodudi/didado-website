package com.website.didado.domain.lostark.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Collectible {
    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "Point")
    private Integer point;

    @JsonProperty(value = "MaxPoint")
    private Integer maxPoint;

    @JsonProperty(value = "CollectiblePoints")
    private List<CollectiblePoint> collectiblePoints;
}
