package com.didado.armory.domain.collectible.domain;

import com.didado.armory.domain.collectible.dto.CollectiblePointParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Collectible {
    @Id
    @GeneratedValue
    @Column(name = "collectible_id")
    private Long id;

    private String type;

    private String icon;

    private Integer point;

    private Integer maxPoint;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "collectible")
    private List<CollectiblePoint> collectiblePoints = new ArrayList<>();

    protected Collectible() {
    }

    @Builder
    public Collectible(String type, String icon, Integer point, Integer maxPoint) {
        this.type = type;
        this.icon = icon;
        this.point = point;
        this.maxPoint = maxPoint;
    }
}
