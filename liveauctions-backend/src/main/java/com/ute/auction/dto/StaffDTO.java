package com.ute.auction.dto;

import java.util.List;

import lombok.Data;

@Data
public class StaffDTO {

    private Long staffId;
    private String position;
    private UserDTO user;
    private List<AuctionDTO> auctions;
    
}
