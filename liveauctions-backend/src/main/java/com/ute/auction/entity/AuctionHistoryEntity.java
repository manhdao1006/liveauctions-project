package com.ute.auction.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ute.auction.entity.impl.AuctionHistoryId;

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
@Table(name = "auction_history")
public class AuctionHistoryEntity {

    @EmbeddedId
    private AuctionHistoryId id;

    @ManyToOne
    @MapsId("auctionId")
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @MapsId("buyerId")
    @JoinColumn(name = "buyer_id")
    private BuyerEntity buyer;

    @ManyToOne
    @MapsId("feeId")
    @JoinColumn(name = "fee_id")
    private FeeEntity fee;

    @ManyToOne
    @MapsId("holidayId")
    @JoinColumn(name = "holiday_id")
    private HolidaysEntity holiday;

    @Column(name = "auctioned_price")
    private BigDecimal auctionedPrice;

    @Column(name = "bid_time")
    private LocalDate bidTime;

    @Column(name = "status")
    private String status;

    @Column(name = "order_date")
    private LocalDate orderDate;
    
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    
    @Column(name = "order_status")
    private String orderStatus;
    
}
