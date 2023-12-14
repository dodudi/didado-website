package com.didado.auction.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class AuctionOption {

    @JsonProperty(value = "MaxItemLevel")
    private Integer maxItemLevel;

    @JsonProperty(value = "ItemGradeQualities")
    private List<Integer> itemGradeQualities;

    @JsonProperty(value = "SkillOptions")
    private List<SkillOption> skillOptions;

    @JsonProperty(value = "EtcOptions")
    private List<EtcOption> etcOptions;

    @JsonProperty(value = "Categories")
    private List<Category> categories;

    @JsonProperty(value = "ItemGrades")
    private List<String> itemGrades;

    @JsonProperty(value = "ItemTiers")
    private List<Integer> itemTiers;

    @JsonProperty(value = "Classes")
    private List<String> classes;
}
