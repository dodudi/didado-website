package com.website.didado.domain.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class RequestAuctionItems {

    @JsonProperty(value = "ItemLevelMin")
    private Integer itemLevelMin;

    @JsonProperty(value = "ItemLevelMax")
    private Integer itemLevelMax;

    @JsonProperty(value = "ItemGradeQuality")
    private Integer itemGradeQuality;

    @JsonProperty(value = "SkillOptions")
    private List<SearchDetailOption> skillOptions;

    @JsonProperty(value = "EtcOptions")
    private List<SearchDetailOption> etcOptions;

    @JsonProperty(value = "Sort")
    private String sort;

    @JsonProperty(value = "CategoryCode")
    private Integer categoryCode;

    @JsonProperty(value = "CharacterClass")
    private String characterClass;

    @JsonProperty(value = "ItemTier")
    private Integer itemTier;

    @JsonProperty(value = "ItemGrade")
    private String itemGrade;

    @JsonProperty(value = "ItemName")
    private String itemName;

    @JsonProperty(value = "PageNo")
    private Integer pageNo;

    @JsonProperty(value = "SortCondition")
    private String sortCondition;
}
