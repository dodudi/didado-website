package com.website.didado.domain.lostark.dto.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MarketList {

    @JsonProperty(value = "PageNo")
    private Integer pageNo;

    @JsonProperty(value = "PageSize")
    private Integer pageSize;

    @JsonProperty(value = "TotalCount")
    private Integer totalCount;

    @JsonProperty(value = "Items")
    private List<MarketItem> items;
}
