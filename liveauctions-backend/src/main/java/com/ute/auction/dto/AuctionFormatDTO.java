package com.ute.auction.dto;

import java.util.List;

import lombok.Data;

@Data

public class AuctionFormatDTO {

    private Integer auctionFormatId;
    private String auctionFormatName;
    private String delFlag;
    private List<RegistrationProductDTO> registrationProducts;
    private List<AuctionDTO> auctions;
    private List<ProductDTO> products;

}
