package com.didado.armory.domain.collectible.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Collectible {
    @Id
    @GeneratedValue
    @Column(name = "collectible_id")
    private Long id;
}
