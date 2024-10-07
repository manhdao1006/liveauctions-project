package com.ute.auction.converter;

import org.springframework.stereotype.Component;

import com.ute.auction.dto.StateDTO;
import com.ute.auction.entity.StateEntity;

@Component
public class StateConverter {

    public StateDTO toDTO(StateEntity entity) {
        if (entity == null) {
            return null;
        }

        StateDTO stateDTO = new StateDTO();
        stateDTO.setStateId(entity.getStateId());
        stateDTO.setStateName(entity.getStateName());

        return stateDTO;
    }

    public StateEntity toEntity(StateDTO dto) {
        if (dto == null) {
            return null;
        }

        StateEntity stateEntity = new StateEntity();
        stateEntity.setStateId(dto.getStateId());
        stateEntity.setStateName(dto.getStateName());

        return stateEntity;
    }

}
