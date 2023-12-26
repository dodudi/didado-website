package com.didado.armory.domain.colosseum.domain;

import com.didado.armory.domain.colosseum.dto.ColosseumParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ColosseumInfo {
    @Id
    @GeneratedValue
    @Column(name = "colosseum_info_id")
    private Long id;

    private Integer rank;

    private Integer preRank;

    private Integer exp;

    @OneToMany
    private List<Colosseum> colosseums = new ArrayList<>();
}
