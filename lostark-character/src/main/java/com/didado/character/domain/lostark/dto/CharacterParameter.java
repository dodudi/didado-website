package com.didado.character.domain.lostark.dto;

import com.didado.character.domain.lostark.domain.CharacterInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CharacterParameter {

    @JsonProperty(value = "ServerName")
    private String serverName;

    @JsonProperty(value = "CharacterName")
    private String characterName;

    @JsonProperty(value = "CharacterLevel")
    private String characterLevel;

    @JsonProperty(value = "CharacterClassName")
    private String characterClassName;

    @JsonProperty(value = "ItemAvgLevel")
    private String itemAvgLevel;

    @JsonProperty(value = "ItemMaxLevel")
    private String itemMaxLevel;

    protected CharacterParameter(){}

    @Builder
    public CharacterParameter(String serverName, String characterName, String characterLevel, String characterClassName, String itemAvgLevel, String itemMaxLevel) {
        this.serverName = serverName;
        this.characterName = characterName;
        this.characterLevel = characterLevel;
        this.characterClassName = characterClassName;
        this.itemAvgLevel = itemAvgLevel;
        this.itemMaxLevel = itemMaxLevel;
    }

    public CharacterInfo toCharacterInfo() {
        return CharacterInfo.builder()
                .serverName(this.serverName)
                .characterName(this.characterName)
                .characterLevel(this.characterLevel)
                .characterClassName(this.characterClassName)
                .itemAvgLevel(this.itemAvgLevel)
                .itemMaxLevel(this.itemMaxLevel)
                .build();
    }


}
