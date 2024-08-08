package com.ute.auction.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.CityDTO;
import com.ute.auction.dto.StateDTO;
import com.ute.auction.entity.CityEntity;
import com.ute.auction.entity.StateEntity;

@Component
public class CityConverter {

    private StateConverter stateConverter;
    
    @Autowired
    public void setStateConverter(@Lazy StateConverter stateConverter) {
        this.stateConverter = stateConverter;
    }

    public CityDTO toDTO(CityEntity entity) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(entity.getId());
        cityDTO.setName(entity.getName());
        cityDTO.setState(toStateDTO(entity.getState()));

        return cityDTO;
    }

    public CityEntity toEntity(CityDTO dto) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(dto.getId());
        cityEntity.setName(dto.getName());
        cityEntity.setState(toStateEntity(dto.getState()));

        return cityEntity;
    }

    private StateEntity toStateEntity(StateDTO stateDTO) {
        return stateConverter.toEntity(stateDTO);
    }

    private StateDTO toStateDTO(StateEntity stateEntity) {
        return stateConverter.toDTO(stateEntity);
    }
    
}
