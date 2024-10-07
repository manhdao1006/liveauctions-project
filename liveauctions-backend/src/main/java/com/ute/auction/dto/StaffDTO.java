package com.ute.auction.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffDTO {

    private Integer staffId;
    private String position;
    private String delFlag;
    private UserDTO user;
    private List<AuctionDTO> auctions;

}
