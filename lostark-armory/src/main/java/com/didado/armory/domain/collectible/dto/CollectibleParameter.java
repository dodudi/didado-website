package com.didado.armory.domain.collectible.dto;

import com.didado.armory.domain.collectible.domain.Collectible;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
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
    private List<CollectiblePointParameter> collectiblePoints = new ArrayList<>();

    protected CollectibleParameter() {
    }

    public CollectibleParameter(Collectible collectible) {
        this.type = collectible.getType();
        this.icon = collectible.getIcon();
        this.point = collectible.getPoint();
        this.maxPoint = collectible.getMaxPoint();
    }

    public Collectible toCollectible() {
        return Collectible.builder()
                .type(type)
                .icon(icon)
                .point(point)
                .maxPoint(maxPoint)
                .build();
    }
}
