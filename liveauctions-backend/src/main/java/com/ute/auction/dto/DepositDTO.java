package com.ute.auction.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DepositDTO {

    private Long auctionId;
    private Long buyerId;
    private AuctionDTO auction;
    private BuyerDTO buyer;
    private BigDecimal depositPercentage;
    
}
