package com.website.didado.domain.lostark.dto.character;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Character {

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
}
