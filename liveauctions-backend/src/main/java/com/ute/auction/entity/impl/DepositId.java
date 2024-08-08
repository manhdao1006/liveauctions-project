package com.ute.auction.entity.impl;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class DepositId implements Serializable{

    private Long auctionId;
    private Long buyerId;
    
}
