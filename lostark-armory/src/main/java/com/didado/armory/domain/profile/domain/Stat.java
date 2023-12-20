package com.didado.armory.domain.profile.domain;

import com.didado.armory.domain.dto.armory.Armory;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "armory_stat")
@Getter
public class Stat {
    @Id
    @GeneratedValue
    @Column(name = "stat_id")
    private Long id;
    private String type;
    private String valuess;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stat")
    private List<Tooltip> toolTip = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "armory_profile_id")
//    private ArmoryProfile armoryProfile;
}
