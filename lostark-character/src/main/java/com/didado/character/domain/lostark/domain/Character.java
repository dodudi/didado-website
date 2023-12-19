package com.didado.character.domain.lostark.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Character {
    @Id
    @GeneratedValue
    @Column(name = "character_id")
    private Long id;

    @OneToMany(mappedBy = "character")
    private List<CharacterInfo> characterInfos = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;
}
