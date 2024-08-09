package com.ute.auction.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.CityDTO;
import com.ute.auction.dto.WareHouseDTO;
import com.ute.auction.entity.CityEntity;
import com.ute.auction.entity.WareHouseEntity;

@Component
public class WareHouseConverter {

    private CityConverter cityConverter;
    
    @Autowired
    public void setCityConverter(@Lazy CityConverter cityConverter) {
        this.cityConverter = cityConverter;
    }

    public WareHouseDTO toDTO(WareHouseEntity entity) {
        WareHouseDTO wareHouseDTO = new WareHouseDTO();
        wareHouseDTO.setId(entity.getId());
        wareHouseDTO.setName(entity.getName());
        wareHouseDTO.setManager(entity.getManager());
        wareHouseDTO.setAddress(entity.getAddress());
        wareHouseDTO.setOperationalStatus(entity.getOperationalStatus());
        wareHouseDTO.setSlotStatus(entity.getSlotStatus());
        wareHouseDTO.setOperatingDay(entity.getOperatingDay());
        wareHouseDTO.setDelFlag(entity.getDelFlag());
        wareHouseDTO.setCity(toCityDTO(entity.getCity()));

        return wareHouseDTO;
    }

    public WareHouseEntity toEntity(WareHouseDTO dto) {
        WareHouseEntity wareHouseEntity = new WareHouseEntity();
        wareHouseEntity.setId(dto.getId());
        wareHouseEntity.setName(dto.getName());
        wareHouseEntity.setManager(dto.getManager());
        wareHouseEntity.setAddress(dto.getAddress());
        wareHouseEntity.setOperationalStatus(dto.getOperationalStatus());
        wareHouseEntity.setSlotStatus(dto.getSlotStatus());
        wareHouseEntity.setOperatingDay(dto.getOperatingDay());
        wareHouseEntity.setDelFlag("1");
        wareHouseEntity.setCity(toCityEntity(dto.getCity()));

        return wareHouseEntity;
    }

    private CityEntity toCityEntity(CityDTO cityDTO) {
        return cityConverter.toEntity(cityDTO);
    }

    private CityDTO toCityDTO(CityEntity cityEntity) {
        return cityConverter.toDTO(cityEntity);
    }
    
}
