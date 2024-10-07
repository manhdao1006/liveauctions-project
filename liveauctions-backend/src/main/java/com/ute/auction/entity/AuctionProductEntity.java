package com.ute.auction.entity;

import com.ute.auction.entity.impl.AuctionProductId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auction_product")
public class AuctionProductEntity {

    @EmbeddedId
    private AuctionProductId id;

    @ManyToOne
    @MapsId("auctionId")
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "slot")
    private Integer slot;

    @Column(name = "status")
    private String status;

    @Column(name = "del_flag", nullable = false)
    private String delFlag = "1";

}
