package com.ute.auction.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class RegistrationProductDTO {

    private Long id;
    private String name;
    private BigDecimal startingPrice;
    private String status;
    private LocalDate registrationDate;
    private String description;
    private String delFlag;
    private SellerDTO seller;
    private SubCategoryDTO subCategory;
    private AuctionFormatDTO auctionFormat;

}
