package com.didado.armory.domain.engraving.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Engraving {
    @Id
    @GeneratedValue
    @Column(name = "engraving_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_engraving_id")
    private ArmoryEngraving armoryEngraving;

    private Integer slot;

    private String name;

    private String icon;

    @Column(name = "tool_tip", length = 100000)
    private String toolTip;

    protected Engraving() {
    }

    @Builder
    public Engraving(Integer slot, String name, String icon, String toolTip) {
        this.slot = slot;
        this.name = name;
        this.icon = icon;
        this.toolTip = toolTip;
    }

    public void changeArmoryEngraving(ArmoryEngraving armoryEngraving) {
        this.armoryEngraving = armoryEngraving;
        armoryEngraving.getEngravings().add(this);
    }
}
