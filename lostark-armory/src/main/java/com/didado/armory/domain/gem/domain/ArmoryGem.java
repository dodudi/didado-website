package com.didado.armory.domain.gem.domain;

import com.didado.armory.domain.info.domain.Armory;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ArmoryGem {
    @Id
    @GeneratedValue
    @Column(name = "armory_gem_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_id")
    private Armory armory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryGem")
    private List<Gem> gems = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryGem")
    private List<GemEffect> effects = new ArrayList<>();

    public void changeArmory(Armory armory) {
        this.armory = armory;
        armory.changeArmoryGem(this);
    }
}
