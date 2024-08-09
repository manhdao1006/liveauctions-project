package com.ute.auction.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ProductDTO {

    private String id;
    private String name;
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
    private List<ImagesDTO> images;
    private List<AuctionProductDTO> auctionProducts;
    private List<AuctionHistoryDTO> auctionHistories;
    
}
