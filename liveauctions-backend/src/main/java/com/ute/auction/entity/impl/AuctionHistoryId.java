package com.ute.auction.entity.impl;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AuctionHistoryId implements Serializable{

    private Long auctionId;
    private String productId;
    private Long buyerId;
    private Long feeId;
    private Long holidayId;
    
}
