package com.ute.auction.entity;

import java.math.BigDecimal;

import com.ute.auction.entity.impl.DepositId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "deposit")
public class DepositEntity {

    @EmbeddedId
    private DepositId id;

    @ManyToOne
    @MapsId("auctionId")
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    @ManyToOne
    @MapsId("buyerId")
    @JoinColumn(name = "buyer_id")
    private BuyerEntity buyer;

    @Column(name = "deposit_percentage")
    private BigDecimal depositPercentage;
    
}
