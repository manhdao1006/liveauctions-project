package com.ute.auction.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "sellers")
public class SellerEntity {

    @Id
    @Column(name = "seller_id")
    private Integer sellerId;

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

    @Column(name = "del_flag", nullable = false)
    private String delFlag = "1";

    @MapsId
    @OneToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "seller_tax", joinColumns = @JoinColumn(name = "seller_id"), inverseJoinColumns = @JoinColumn(name = "tax_id"))
    private List<TaxEntity> taxes = new ArrayList<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistrationProductEntity> registrationProducts = new ArrayList<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> products = new ArrayList<>();

}
