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
@Table(name = "seller")
public class SellerEntity {

    @Id
    private Long sellerId;

    @Column(name = "tax_code")
    private String taxCode;

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

    @MapsId
    @OneToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SellerTaxEntity> sellerTaxes = new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<RegistrationProductEntity> registrationProducts = new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<ProductEntity> products = new ArrayList<>();
    
}
