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
public class FeeDTO {

    private Integer feeId;
    private String feeName;
    private BigDecimal cost;
    private String description;
    private List<AuctionHistoryDTO> auctionHistories;

}
