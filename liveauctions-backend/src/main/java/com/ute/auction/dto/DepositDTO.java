package com.ute.auction.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositDTO {

    private AuctionDTO auction;
    private BuyerDTO buyer;
    private BigDecimal depositPercentage;

}
