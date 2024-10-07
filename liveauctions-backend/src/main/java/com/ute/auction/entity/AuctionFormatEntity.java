package com.ute.auction.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auction_formats")
public class AuctionFormatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_format_id")
    private Integer auctionFormatId;

    @Column(name = "auction_format_name")
    private String auctionFormatName;

    @Column(name = "del_flag", nullable = false)
    private String delFlag = "1";

    @OneToMany(mappedBy = "auctionFormat")
    private List<RegistrationProductEntity> registrationProducts = new ArrayList<>();

    @OneToMany(mappedBy = "auctionFormat")
    private List<AuctionEntity> auctions = new ArrayList<>();

    @OneToMany(mappedBy = "auctionFormat")
    private List<ProductEntity> products = new ArrayList<>();

}
