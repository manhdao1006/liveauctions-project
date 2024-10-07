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
public class CityDTO {

    private Integer cityId;
    private String cityName;
    private StateDTO state;
    private List<WareHouseDTO> wareHouses;
    private List<UserDTO> users;

}
