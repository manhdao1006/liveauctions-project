package com.ute.auction.converter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.CityDTO;
import com.ute.auction.dto.StateDTO;
import com.ute.auction.entity.CityEntity;
import com.ute.auction.entity.StateEntity;

@Component
public class CityConverter {

    private final StateConverter stateConverter;

    public CityConverter(@Lazy StateConverter stateConverter) {
        this.stateConverter = stateConverter;
    }

    public CityDTO toDTO(CityEntity entity) {
        if (entity == null) {
            return null;
        }

        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityId(entity.getCityId());
        cityDTO.setCityName(entity.getCityName());
        cityDTO.setState(toStateDTO(entity.getState()));

        return cityDTO;
    }

    public CityEntity toEntity(CityDTO dto) {
        if (dto == null) {
            return null;
        }

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCityId(dto.getCityId());
        cityEntity.setCityName(dto.getCityName());
        cityEntity.setState(toStateEntity(dto.getState()));

        return cityEntity;
    }

    private StateEntity toStateEntity(StateDTO stateDTO) {
        if (stateDTO == null) {
            return null;
        }
        return stateConverter.toEntity(stateDTO);
    }

    private StateDTO toStateDTO(StateEntity stateEntity) {
        if (stateEntity == null) {
            return null;
        }
        return stateConverter.toDTO(stateEntity);
    }

}
