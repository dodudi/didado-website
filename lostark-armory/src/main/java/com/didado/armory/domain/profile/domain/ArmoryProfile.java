//package com.didado.armory.domain.profile.domain;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//public class ArmoryProfile {
//    @Id
//    @GeneratedValue
//    @Column(name = "armory_profile_id")
//    private Long id;
//
//    private String characterImage;
//
//    private Integer expeditionLevel;
//
//    private String pvpGradeName;
//
//    private Integer townLevel;
//
//    private String townName;
//
//    private String title;
//
//    private String guildMemberGrade;
//
//    private String guildName;
//
//    private Integer usingSkillPoint;
//
//    private Integer totalSkillPoint;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryProfile")
//    private List<Stat> statParameters  = new ArrayList<>();
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryProfile")
//    private List<Tendency> tendencies  = new ArrayList<>();
//
//    private String serverName;
//
//    private String characterName;
//
//    private Integer characterLevel;
//
//    private String characterClassName;
//
//    private String itemAvgLevel;
//
//    private String itemMaxLevel;
//}
