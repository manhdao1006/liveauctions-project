package com.ute.auction.dto;

import java.util.List;

import lombok.Data;

@Data
public class AuctionFormatDTO {

    private Long id;
    private String name;
    private List<RegistrationProductDTO> registrationProducts;
    private List<AuctionDTO> auctions;
    private List<ProductDTO> products;
    
}
