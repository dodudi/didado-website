package com.didado.armory.domain.collectible.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collectible_info_id")
    private CollectibleInfo collectibleInfo;

    protected Collectible() {
    }

    @Builder
    public Collectible(String type, String icon, Integer point, Integer maxPoint) {
        this.type = type;
        this.icon = icon;
        this.point = point;
        this.maxPoint = maxPoint;
    }

    public void changeCollectibleInfo(CollectibleInfo collectibleInfo) {
        this.collectibleInfo = collectibleInfo;
        collectibleInfo.getCollectibles().add(this);
    }

    public void deleteCollectibleInfo() {
        collectibleInfo.getCollectibles().remove(this);
        this.collectibleInfo = null;
    }

    public void changeData(Collectible collectible) {
        this.type = collectible.getType();
        this.icon = collectible.getIcon();
        this.point = collectible.getPoint();
        this.maxPoint = collectible.getMaxPoint();
    }
}
