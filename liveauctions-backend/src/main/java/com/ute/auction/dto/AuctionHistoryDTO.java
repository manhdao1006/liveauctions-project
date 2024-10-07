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
public class AuctionHistoryDTO {

    private AuctionDTO auction;
    private ProductDTO product;
    private BuyerDTO buyer;
    private FeeDTO fee;
    private HolidayDTO holiday;
    private BigDecimal auctionedPrice;
    private LocalDate bidTime;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String orderStatus;

}
