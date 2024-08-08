package com.ute.auction.converter;

import org.springframework.stereotype.Component;

import com.ute.auction.dto.StateDTO;
import com.ute.auction.entity.StateEntity;

@Component
public class StateConverter {

    public StateDTO toDTO(StateEntity entity) {
        StateDTO stateDTO = new StateDTO();
        stateDTO.setId(entity.getId());
        stateDTO.setName(entity.getName());

        return stateDTO;
    }

    public StateEntity toEntity(StateDTO dto) {
        StateEntity stateEntity = new StateEntity();
        stateEntity.setId(dto.getId());
        stateEntity.setName(dto.getName());

        return stateEntity;
    }
    
}
