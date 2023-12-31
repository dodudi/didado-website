package com.didado.armory.domain.profile.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(of = {"id", "characterName", "stats", "tendencies"})
public class ArmoryProfile {
    @Id
    @GeneratedValue
    @Column(name = "armory_profile_id")
    private Long id;

    private String characterName;

    private String characterImage;

    private Integer expeditionLevel;

    private String pvpGradeName;

    private Integer townLevel;

    private String townName;

    private String title;

    private String guildMemberGrade;

    private String guildName;

    private Integer usingSkillPoint;

    private Integer totalSkillPoint;

    private String serverName;

    private Integer characterLevel;

    private String characterClassName;

    private String itemAvgLevel;

    private String itemMaxLevel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryProfile")
    private List<Stat> stats = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "armoryProfile")
    private List<Tendency> tendencies = new ArrayList<>();

    protected ArmoryProfile() {
    }

    @Builder
    public ArmoryProfile(String characterImage, Integer expeditionLevel, String pvpGradeName, Integer townLevel, String townName, String title, String guildMemberGrade, String guildName, Integer usingSkillPoint, Integer totalSkillPoint, String serverName, String characterName, Integer characterLevel, String characterClassName, String itemAvgLevel, String itemMaxLevel) {
        this.characterImage = characterImage;
        this.expeditionLevel = expeditionLevel;
        this.pvpGradeName = pvpGradeName;
        this.townLevel = townLevel;
        this.townName = townName;
        this.title = title;
        this.guildMemberGrade = guildMemberGrade;
        this.guildName = guildName;
        this.usingSkillPoint = usingSkillPoint;
        this.totalSkillPoint = totalSkillPoint;
        this.serverName = serverName;
        this.characterName = characterName;
        this.characterLevel = characterLevel;
        this.characterClassName = characterClassName;
        this.itemAvgLevel = itemAvgLevel;
        this.itemMaxLevel = itemMaxLevel;
    }


    public void changeData(ArmoryProfile armoryProfile) {
        this.characterImage = armoryProfile.getCharacterImage();
        this.expeditionLevel = armoryProfile.getExpeditionLevel();
        this.pvpGradeName = armoryProfile.getPvpGradeName();
        this.townLevel = armoryProfile.getTownLevel();
        this.townName = armoryProfile.getTownName();
        this.title = armoryProfile.getTitle();
        this.guildMemberGrade = armoryProfile.getGuildMemberGrade();
        this.guildName = armoryProfile.getGuildName();
        this.usingSkillPoint = armoryProfile.getUsingSkillPoint();
        this.totalSkillPoint = armoryProfile.getTotalSkillPoint();
        this.serverName = armoryProfile.getServerName();
        this.characterName = armoryProfile.getCharacterName();
        this.characterLevel = armoryProfile.getCharacterLevel();
        this.characterClassName = armoryProfile.getCharacterClassName();
        this.itemAvgLevel = armoryProfile.getItemAvgLevel();
        this.itemMaxLevel = armoryProfile.getItemMaxLevel();
    }
}
