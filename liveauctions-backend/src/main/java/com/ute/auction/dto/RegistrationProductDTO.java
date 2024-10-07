package com.ute.auction.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationProductDTO {

    private Integer registrationProductId;
    private String registrationProductName;
    private BigDecimal startingPrice;
    private String status;
    private LocalDate registrationDate;
    private String description;
    private String delFlag;
    private SellerDTO seller;
    private SubCategoryDTO subCategory;
    private AuctionFormatDTO auctionFormat;

}
