package com.didado.armory.domain.engraving.domain;

import com.didado.armory.domain.engraving.dto.EngravingEffectParameter;
import com.didado.armory.domain.engraving.dto.EngravingParameter;
import com.didado.armory.domain.info.domain.Armory;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
public class ArmoryEngraving {
    @Id
    @GeneratedValue
    @Column(name = "armory_engraving_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_id")
    private Armory armory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryEngraving")
    private List<Engraving> engravings = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryEngraving")
    private List<EngravingEffect> effects = new ArrayList<>();

    public void changeArmory(Armory armory) {
        this.armory = armory;
        armory.changeArmoryEngraving(this);
    }
}
