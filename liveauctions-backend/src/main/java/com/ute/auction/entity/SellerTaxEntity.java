package com.ute.auction.entity;

import com.ute.auction.entity.impl.SellerTaxId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "seller_tax")
public class SellerTaxEntity {

    @EmbeddedId
    private SellerTaxId id;
    
    @ManyToOne
    @MapsId("sellerId")
    @JoinColumn(name = "seller_id")
    private SellerEntity seller;

    @ManyToOne
    @MapsId("taxId")
    @JoinColumn(name = "tax_id")
    private TaxEntity tax;
    
}
