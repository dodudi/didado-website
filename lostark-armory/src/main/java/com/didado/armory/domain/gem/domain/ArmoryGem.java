package com.didado.armory.domain.gem.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class ArmoryGem {
    @Id
    @GeneratedValue
    @Column(name = "armory_gem_id")
    private Long id;

    @OneToMany
    private List<Gem> gems;

    @OneToMany
    private List<GemEffect> effects;
}
