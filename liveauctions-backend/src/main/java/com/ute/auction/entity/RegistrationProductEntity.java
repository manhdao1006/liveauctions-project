package com.ute.auction.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registration_products")
public class RegistrationProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_product_id")
    private Integer registrationProductId;

    @Column(name = "registration_product_name")
    private String registrationProductName;

    @Column(name = "starting_price")
    private BigDecimal startingPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "del_flag", nullable = false)
    private String delFlag = "1";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private SellerEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategoryEntity subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_format_id")
    private AuctionFormatEntity auctionFormat;

}
