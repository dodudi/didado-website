package com.didado.auction.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Auction {

    @JsonProperty(value = "PageNo")
    private Integer pageNo;

    @JsonProperty(value = "PageSize")
    private Integer pageSize;

    @JsonProperty(value = "TotalCount")
    private Integer totalCount;

    @JsonProperty(value = "Items")
    private List<AuctionItem> items;
}
