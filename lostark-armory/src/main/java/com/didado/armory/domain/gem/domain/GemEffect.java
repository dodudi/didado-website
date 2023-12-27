package com.didado.armory.domain.gem.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter

public class GemEffect {
    @Id
    @GeneratedValue
    @Column(name = "gem_effect_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_gem_id")
    private ArmoryGem armoryGem;

    private String gemSlot;

    private String name;

    private String description;

    private String icon;
    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

    protected GemEffect() {
    }

    @Builder
    public GemEffect(String gemSlot, String name, String description, String icon, String toolTip) {
        this.gemSlot = gemSlot;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.toolTip = toolTip;
    }

    public void changeArmoryGem(ArmoryGem armoryGem) {
        this.armoryGem = armoryGem;
        armoryGem.getEffects().add(this);
    }
}
