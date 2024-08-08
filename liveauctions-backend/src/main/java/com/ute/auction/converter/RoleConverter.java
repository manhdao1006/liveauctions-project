package com.ute.auction.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ute.auction.dto.RoleDTO;
import com.ute.auction.entity.RoleEntity;

@Component
public class RoleConverter {

    public RoleDTO toDTO(RoleEntity entity) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(entity.getId());
        roleDTO.setName(entity.getName());

        return roleDTO;
    }

    public RoleEntity toEntity(RoleDTO dto) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(dto.getId());
        roleEntity.setName(dto.getName());

        return roleEntity;
    }

    public List<RoleDTO> toDTOs(List<RoleEntity> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<RoleEntity> toEntities(List<RoleDTO> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
    
}
