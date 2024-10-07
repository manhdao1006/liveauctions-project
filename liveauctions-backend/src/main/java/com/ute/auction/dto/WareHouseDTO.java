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
public class WareHouseDTO {

    private Integer warehouseId;
    private String warehouseName;
    private String manager;
    private String address;
    private String operationalStatus;
    private String slotStatus;
    private String operatingDay;
    private String delFlag;
    private CityDTO city;
    private List<ProductDTO> products;

}
