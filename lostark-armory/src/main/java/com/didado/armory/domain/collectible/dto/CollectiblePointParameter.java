package com.didado.armory.domain.collectible.dto;

import com.didado.armory.domain.collectible.domain.CollectiblePoint;
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

    protected CollectiblePointParameter() {
    }

    public CollectiblePointParameter(CollectiblePoint collectiblePoint) {
        this.pointName = collectiblePoint.getPointName();
        this.point = collectiblePoint.getPoint();
        this.maxPoint = collectiblePoint.getMaxPoint();
    }

    public CollectiblePoint toCollectiblePoint() {
        return CollectiblePoint.builder()
                .pointName(pointName)
                .point(point)
                .maxPoint(maxPoint)
                .build();
    }
}
