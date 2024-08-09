package com.ute.auction.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "buyer")
public class BuyerEntity {

    @Id
    private Long buyerId;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "name_on_card")
    private String nameOnCard;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "issuing_bank")
    private String issuingBank;

    @Column(name = "expiration")
    private LocalDate expiration;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "del_flag")
    private String delFlag;

    @MapsId
    @OneToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepositEntity> deposits = new ArrayList<>();

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuctionHistoryEntity> auctionHistories = new ArrayList<>();
    
}
