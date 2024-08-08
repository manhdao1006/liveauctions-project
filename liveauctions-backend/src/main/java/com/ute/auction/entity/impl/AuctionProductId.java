package com.ute.auction.entity.impl;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AuctionProductId implements Serializable{

    private Long auctionId;
    private String productId;
    
}
