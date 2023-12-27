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

import java.util.List;

@Entity
@Getter
public class Armory {
    @Id
    @GeneratedValue
    @Column(name = "armory_id")
    private Long id;

    private String characterName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_profile_id")
    private ArmoryProfile armoryProfile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armory")
    private List<ArmoryEquipment> armoryEquipment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armory")
    private List<ArmoryAvatar> armoryAvatars;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ArmorySkill> armorySkills;

    @OneToOne(fetch = FetchType.LAZY)
    private ArmoryEngraving armoryEngraving;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armory_card_id")
    private ArmoryCard armoryCard;

    @OneToOne(fetch = FetchType.LAZY)
    private ArmoryGem armoryGem;

    @OneToOne(fetch = FetchType.LAZY)
    private ColosseumInfo colosseumInfo;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Collectible> collectibles;

    protected Armory() {
    }

    public Armory(String characterName) {
        this.characterName = characterName;
    }

    public void changeArmoryProfile(ArmoryProfile armoryProfile) {
        this.armoryProfile = armoryProfile;
    }

    public void changeArmoryCard(ArmoryCard armoryCard) {
        this.armoryCard = armoryCard;
    }
}
