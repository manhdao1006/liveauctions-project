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

import lombok.Data;

@Data
@Entity
@Table(name = "auction_format")
public class AuctionFormatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "del_flag")
    private String delFlag;

    @OneToMany(mappedBy = "auctionFormat")
    private List<RegistrationProductEntity> registrationProducts = new ArrayList<>();

    @OneToMany(mappedBy = "auctionFormat")
    private List<AuctionEntity> auctions = new ArrayList<>();

    @OneToMany(mappedBy = "auctionFormat")
    private List<ProductEntity> products = new ArrayList<>();
    
}
