package com.didado.armory.domain.engraving.domain;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryEngraving")
    private List<Engraving> engravings = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryEngraving")
    private List<EngravingEffect> effects = new ArrayList<>();
}
