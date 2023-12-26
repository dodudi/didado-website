//package com.didado.armory.domain.info.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//
//import java.util.List;
//
//@Entity
//@Getter
//public class Armory {
//    @Id
//    @GeneratedValue
//    @Column(name = "armory_id")
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    private ArmoryProfile armoryProfile;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<ArmoryEquipment> armoryEquipment;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<ArmoryAvatar> armoryAvatars;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<ArmorySkill> armorySkills;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    private ArmoryEngraving armoryEngraving;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    private ArmoryCard armoryCard;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    private ArmoryGem armoryGem;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    private ColosseumInfo colosseumInfo;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Collectible> collectibles;
//}
