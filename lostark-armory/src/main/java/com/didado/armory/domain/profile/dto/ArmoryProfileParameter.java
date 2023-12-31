package com.didado.armory.domain.profile.dto;

import com.didado.armory.domain.profile.domain.ArmoryProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
public class ArmoryProfileParameter {
    @JsonProperty(value = "CharacterImage")
    private String characterImage;

    @JsonProperty(value = "ExpeditionLevel")
    private Integer expeditionLevel;

    @JsonProperty(value = "PvpGradeName")
    private String pvpGradeName;

    @JsonProperty(value = "TownLevel")
    private Integer townLevel;

    @JsonProperty(value = "TownName")
    private String townName;

    @JsonProperty(value = "Title")
    private String title;

    @JsonProperty(value = "GuildMemberGrade")
    private String guildMemberGrade;

    @JsonProperty(value = "GuildName")
    private String guildName;

    @JsonProperty(value = "UsingSkillPoint")
    private Integer usingSkillPoint;

    @JsonProperty(value = "TotalSkillPoint")
    private Integer totalSkillPoint;

    @JsonProperty(value = "ServerName")
    private String serverName;

    @JsonProperty(value = "CharacterName")
    private String characterName;

    @JsonProperty(value = "CharacterLevel")
    private Integer characterLevel;

    @JsonProperty(value = "CharacterClassName")
    private String characterClassName;

    @JsonProperty(value = "ItemAvgLevel")
    private String itemAvgLevel;

    @JsonProperty(value = "ItemMaxLevel")
    private String itemMaxLevel;

    @JsonProperty(value = "Stats")
    private List<StatParameter> stats;

    @JsonProperty(value = "Tendencies")
    private List<TendencyParameter> tendencies;

    public ArmoryProfile toArmoryProfile() {
        return ArmoryProfile.builder()
                .characterName(this.characterName)
                .characterImage(this.characterImage)
                .expeditionLevel(this.expeditionLevel)
                .pvpGradeName(this.pvpGradeName)
                .townLevel(this.townLevel)
                .townName(this.townName)
                .title(this.title)
                .guildMemberGrade(this.guildMemberGrade)
                .guildName(this.guildName)
                .usingSkillPoint(this.usingSkillPoint)
                .totalSkillPoint(this.totalSkillPoint)
                .serverName(this.serverName)
                .characterLevel(this.characterLevel)
                .characterClassName(this.characterClassName)
                .itemAvgLevel(this.itemAvgLevel)
                .itemMaxLevel(this.itemMaxLevel)
                .build();
    }
}
