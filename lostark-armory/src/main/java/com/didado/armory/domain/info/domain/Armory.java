package com.didado.armory.domain.info.domain;

import com.didado.armory.domain.avatar.domain.ArmoryAvatar;
import com.didado.armory.domain.card.domain.ArmoryCard;
import com.didado.armory.domain.collectible.domain.Collectible;
import com.didado.armory.domain.colosseum.domain.ColosseumInfo;
import com.didado.armory.domain.engraving.domain.ArmoryEngraving;
import com.didado.armory.domain.equipment.domain.ArmoryEquipment;
import com.didado.armory.domain.gem.domain.ArmoryGem;
import com.didado.armory.domain.profile.domain.ArmoryProfile;
import com.didado.armory.domain.skill.domain.ArmorySkill;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Armory {
    @Id
    @GeneratedValue
    @Column(name = "armory_id")
    private Long id;

    private String characterName;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "armory")
    private ArmoryProfile armoryProfile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armory")
    private List<ArmoryEquipment> armoryEquipment = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armory")
    private List<ArmoryAvatar> armoryAvatars = new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armory")
    private List<ArmorySkill> armorySkills = new ArrayList<>();;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "armory")
    private ArmoryEngraving armoryEngraving;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "armory")
    private ArmoryCard armoryCard;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "armory")
    private ArmoryGem armoryGem;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "armory")
    private ColosseumInfo colosseumInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armory")
    private List<Collectible> collectibles = new ArrayList<>();;

    protected Armory() {
    }

    public Armory(String characterName) {
        this.characterName = characterName;
    }

    public void changeArmoryProfile(ArmoryProfile armoryProfile) {
        this.armoryProfile = armoryProfile;
    }

    public void changeArmoryEngraving(ArmoryEngraving armoryEngraving) {
        this.armoryEngraving = armoryEngraving;
    }

    public void changeArmoryCard(ArmoryCard armoryCard) {
        this.armoryCard = armoryCard;
    }

    public void changeArmoryGem(ArmoryGem armoryGem) {
        this.armoryGem = armoryGem;
    }

    public void changeColosseumInfo(ColosseumInfo colosseumInfo) {
        this.colosseumInfo = colosseumInfo;
    }
}
