package com.didado.armory.domain.engraving.domain;

import com.didado.armory.domain.engraving.dto.EngravingEffectParameter;
import com.didado.armory.domain.engraving.dto.EngravingParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@ToString
public class ArmoryEngraving {
    @Id
    @GeneratedValue
    @Column(name = "armory_engraving_id")
    private Long id;

    @OneToMany
    private List<Engraving> engravings;

    @OneToMany
    private List<EngravingEffect> effects;
}
