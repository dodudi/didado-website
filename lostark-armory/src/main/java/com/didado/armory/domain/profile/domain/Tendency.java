//package com.didado.armory.domain.profile.domain;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
//import lombok.Getter;
//
//@Entity
//@Getter
//public class Tendency {
//    @Id
//    @GeneratedValue
//    @Column(name = "tendency_id")
//    private Long id;
//
//    private String type;
//
//    private Integer point;
//
//    private Integer maxPoint;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "armory_profile_id")
//    private ArmoryProfile armoryProfile;
//}
