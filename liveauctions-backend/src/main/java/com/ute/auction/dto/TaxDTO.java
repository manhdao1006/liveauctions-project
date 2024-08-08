package com.ute.auction.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class TaxDTO {

    private Long id;
    private String name;
    private BigDecimal cost;
    private String description;
    private List<SellerTaxDTO> sellerTaxes;
    
}
