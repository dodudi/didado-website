package com.didado.auction.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class AuctionItem {

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Grade")
    private String grade;

    @JsonProperty(value = "Tier")
    private Integer tier;

    @JsonProperty(value = "Level")
    private Integer level;

    @JsonProperty(value = "Icon")
    private String icon;

    @JsonProperty(value = "GradeQuality")
    private Integer gradeQuality;

    @JsonProperty(value = "AuctionInfo")
    private AuctionInfo auctionInfo;

    @JsonProperty(value = "Options")
    private List<ItemOption> options;
}
