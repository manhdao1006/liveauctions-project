package com.ute.auction.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String productId;
    private String productName;
    private BigDecimal startingPrice;
    private String status;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String description;
    private String delFlag;
    private SellerDTO seller;
    private SubCategoryDTO subCategory;
    private AuctionFormatDTO auctionFormat;
    private WareHouseDTO wareHouse;
    private AppraiserDTO appraiser;
    private List<ImageDTO> images;
    private List<AuctionProductDTO> auctionProducts;
    private List<AuctionHistoryDTO> auctionHistories;

}
