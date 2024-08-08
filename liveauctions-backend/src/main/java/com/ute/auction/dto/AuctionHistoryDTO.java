package com.ute.auction.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class AuctionHistoryDTO {

    private AuctionDTO auction;
    private ProductDTO product;
    private BuyerDTO buyer;
    private FeeDTO fee;
    private HolidaysDTO holiday;
    private BigDecimal auctionedPrice;
    private LocalDate bidTime;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String orderStatus;
    
}
