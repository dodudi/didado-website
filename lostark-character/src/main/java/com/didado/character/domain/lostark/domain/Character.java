package com.didado.character.domain.lostark.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
public class Character {
    @Id
    @GeneratedValue
    @Column(name = "character_id")
    private Long id;

    @OneToMany(mappedBy = "character")
    private List<CharacterInfo> characterInfos = new ArrayList<>();
}
