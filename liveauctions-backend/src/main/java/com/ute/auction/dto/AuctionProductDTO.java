package com.ute.auction.dto;

import lombok.Data;

@Data
public class AuctionProductDTO {

    private AuctionDTO auction;
    private ProductDTO product;
    private Long slots;
    private String status;
    
}
