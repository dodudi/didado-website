package com.didado.content.domain.lostark.dto.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MarketParameter {

    @JsonProperty(value = "Sort")
    private String sort;

    @JsonProperty(value = "CategoryCode")
    private String categoryCode;

    @JsonProperty(value = "CharacterClass")
    private String characterClass;

    @JsonProperty(value = "ItemTier")
    private String itemTier;

    @JsonProperty(value = "ItemGrade")
    private String itemGrade;

    @JsonProperty(value = "ItemName")
    private String itemName;

    @JsonProperty(value = "PageNo")
    private String pageNo;

    @JsonProperty(value = "SortCondition")
    private String sortCondition;
}
