package com.didado.armory.domain.gem.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Gem {
    @Id
    @GeneratedValue
    @Column(name = "gem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_gem_id")
    private ArmoryGem armoryGem;

    private String slot;
    private String name;
    private String icon;
    private String level;
    private String grade;
    @Column(name = "tool_tip", length = 10000)
    private String toolTip;

    protected Gem() {
    }

    @Builder
    public Gem(String slot, String name, String icon, String level, String grade, String toolTip) {
        this.slot = slot;
        this.name = name;
        this.icon = icon;
        this.level = level;
        this.grade = grade;
        this.toolTip = toolTip;
    }

    public void changeArmoryGem(ArmoryGem armoryGem) {
        this.armoryGem = armoryGem;
        armoryGem.getGems().add(this);
    }
}
