package com.ute.auction.dto;

import java.util.List;

import com.ute.auction.entity.AuctionHistoryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDTO {

    private Integer holidayId;
    private String holidayName;
    private String breakTime;
    private List<AuctionHistoryEntity> auctionHistories;

}
