package com.ute.auction.dto;

import lombok.Data;

@Data
public class SellerTaxDTO {

    private Long sellerId;
    private Long taxId;
    private SellerDTO seller;
    private TaxDTO tax;
    
}
