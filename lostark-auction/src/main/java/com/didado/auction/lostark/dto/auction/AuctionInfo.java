package com.didado.auction.lostark.dto.auction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AuctionInfo {

    @JsonProperty(value = "StartPrice")
    private Integer startPrice;

    @JsonProperty(value = "BuyPrice")
    private Integer buyPrice;

    @JsonProperty(value = "BidPrice")
    private Integer bidPrice;

    @JsonProperty(value = "EndDate")
    private String endDate;

    @JsonProperty(value = "BidCount")
    private Integer bidCount;

    @JsonProperty(value = "BidStartPrice")
    private Integer bidStartPrice;

    @JsonProperty(value = "IsCompetitive")
    private Boolean isCompetitive;

    @JsonProperty(value = "TradeAllowCount")
    private Integer tradeAllowCount;
}
