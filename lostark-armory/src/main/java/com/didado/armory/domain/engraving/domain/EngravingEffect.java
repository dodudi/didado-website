package com.didado.armory.domain.engraving.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class EngravingEffect {
    @Id
    @GeneratedValue
    @Column(name = "engraving_effect_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_engraving_id")
    private ArmoryEngraving armoryEngraving;

    private String icon;

    private String name;

    private String description;

    protected EngravingEffect() {
    }

    @Builder
    public EngravingEffect(String icon, String name, String description) {
        this.icon = icon;
        this.name = name;
        this.description = description;
    }

    public void changeArmoryEngraving(ArmoryEngraving armoryEngraving) {
        this.armoryEngraving = armoryEngraving;
        armoryEngraving.getEffects().add(this);
    }

}
