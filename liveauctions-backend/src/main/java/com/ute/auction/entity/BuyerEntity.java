package com.ute.auction.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buyers")
public class BuyerEntity {

    @Id
    @Column(name = "buyer_id")
    private Integer buyerId;

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

    @Column(name = "del_flag", nullable = false)
    private String delFlag = "1";

    @MapsId
    @OneToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "buyer")
    private List<DepositEntity> deposits = new ArrayList<>();

    @OneToMany(mappedBy = "buyer")
    private List<AuctionHistoryEntity> auctionHistories = new ArrayList<>();

}
