package com.ute.auction.dto;

import java.util.List;

import lombok.Data;

@Data
public class CityDTO {

    private Long id;
    private String name;
    private StateDTO state;
    private List<WareHouseDTO> wareHouses;
    private List<UserDTO> users;
    
}
