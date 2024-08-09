package com.ute.auction.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "starting_price")
    private BigDecimal startingPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "min_price")
    private BigDecimal minPrice;

    @Column(name = "max_price")
    private BigDecimal maxPrice;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "del_flag")
    private String delFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private SellerEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategoryEntity subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_format_id")
    private AuctionFormatEntity auctionFormat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private WareHouseEntity wareHouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appraiser_id")
    private AppraiserEntity appraiser;

    @OneToMany(mappedBy = "product")
    private List<ImagesEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuctionProductEntity> auctionProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuctionHistoryEntity> auctionHistories = new ArrayList<>();
    
}
