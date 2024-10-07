package com.ute.auction.entity.impl;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AuctionHistoryId implements Serializable {

    private Integer auctionId;
    private String productId;
    private Integer buyerId;
    private Integer feeId;
    private Integer holidayId;

}
