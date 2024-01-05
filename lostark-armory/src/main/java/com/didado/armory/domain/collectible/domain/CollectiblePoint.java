package com.didado.armory.domain.collectible.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class CollectiblePoint {
    @Id
    @GeneratedValue
    @Column(name = "collectible_point_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collectible_id")
    private Collectible collectible;

    private String pointName;

    private Integer point;

    private Integer maxPoint;

    protected CollectiblePoint() {
    }

    @Builder
    public CollectiblePoint(String pointName, Integer point, Integer maxPoint) {
        this.pointName = pointName;
        this.point = point;
        this.maxPoint = maxPoint;
    }

    public void changeCollectible(Collectible collectible) {
        this.collectible = collectible;
        collectible.getCollectiblePoints().add(this);
    }

    public void deleteCollectible() {
        collectible.getCollectiblePoints().remove(this);
        this.collectible = null;
    }

    public void changeData(CollectiblePoint collectiblePoint) {
        this.pointName = collectiblePoint.getPointName();
        this.point = collectiblePoint.getPoint();
        this.maxPoint = collectiblePoint.getMaxPoint();
    }
}
