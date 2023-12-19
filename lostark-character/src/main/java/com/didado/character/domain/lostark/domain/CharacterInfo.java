package com.didado.character.domain.lostark.domain;

import com.didado.character.domain.lostark.dto.CharacterParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString(exclude = {"character"})
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;

    protected CharacterInfo() {
    }

    @Builder
    public CharacterInfo(String serverName, String characterName, String characterLevel, String characterClassName, String itemAvgLevel, String itemMaxLevel) {
        this.serverName = serverName;
        this.characterName = characterName;
        this.characterLevel = characterLevel;
        this.characterClassName = characterClassName;
        this.itemAvgLevel = itemAvgLevel;
        this.itemMaxLevel = itemMaxLevel;
    }

    public void updateData(CharacterParameter characterInfo) {
        this.serverName = characterInfo.getServerName();
        this.characterName = characterInfo.getCharacterName();
        this.characterLevel = characterInfo.getCharacterLevel();
        this.characterClassName = characterInfo.getCharacterClassName();
        this.itemAvgLevel = characterInfo.getItemAvgLevel();
        this.itemMaxLevel = characterInfo.getItemMaxLevel();
    }

    public CharacterInfo updateCharacter(Character character) {
        this.character = character;
        character.getCharacterInfos().add(this);
        return this;
    }
}
