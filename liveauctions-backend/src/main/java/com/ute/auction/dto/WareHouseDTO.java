package com.ute.auction.dto;

import java.util.List;

import lombok.Data;

@Data
public class WareHouseDTO {

    private Long id;
    private String name;
    private String manager;
    private String address;
    private String operationalStatus;
    private String slotStatus;
    private String operatingDay;
    private String delFlag;
    private CityDTO city;
    private List<ProductDTO> products;
    
}
