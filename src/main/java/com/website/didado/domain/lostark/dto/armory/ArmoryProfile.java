package com.website.didado.domain.lostark.dto.armory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArmoryProfile {
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

    @JsonProperty(value = "Stats")
    private List<Stat> stats;

    @JsonProperty(value = "Tendencies")
    private List<Tendency> tendencies;

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


}
