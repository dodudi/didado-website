package com.didado.character.domain.lostark.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class CharacterInfo {
    @Id
    @GeneratedValue
    @Column(name = "character_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    private String serverName;
    private String characterName;
    private String characterLevel;
    private String characterClassName;
    private String itemAvgLevel;
    private String itemMaxLevel;
}
