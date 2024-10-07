package com.ute.auction.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDTO {

    private Integer auctionId;
    private String auctionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal buyerPremium;
    private String status;
    private String description;
    private String delFlag;
    private StaffDTO staff;
    private AuctionFormatDTO auctionFormat;
    private List<AuctionProductDTO> auctionProducts;
    private List<DepositDTO> deposits;
    private List<AuctionHistoryDTO> auctionHistories;

}
