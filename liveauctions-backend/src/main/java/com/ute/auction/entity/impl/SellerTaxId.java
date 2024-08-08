package com.ute.auction.entity.impl;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class SellerTaxId implements Serializable{
    
    private Long sellerId;
    private Long taxId;
    
}
