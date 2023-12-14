package com.didado.market.domain.lostark.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RequestMarketItems {

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
