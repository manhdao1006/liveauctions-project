package com.ute.auction.dto;

import java.util.List;

import com.ute.auction.entity.AuctionHistoryEntity;

import lombok.Data;

@Data
public class HolidayDTO {

    private Long id;
    private String name;
    private String breakTime;
    private List<AuctionHistoryEntity> auctionHistories;
    
}
