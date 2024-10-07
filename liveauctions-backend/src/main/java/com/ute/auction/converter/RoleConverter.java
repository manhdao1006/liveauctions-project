package com.ute.auction.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ute.auction.dto.RoleDTO;
import com.ute.auction.entity.RoleEntity;

@Component
public class RoleConverter {

    public RoleDTO toDTO(RoleEntity entity) {
        if (entity == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(entity.getRoleId());
        roleDTO.setRoleName(entity.getRoleName());

        return roleDTO;
    }

    public RoleEntity toEntity(RoleDTO dto) {
        if (dto == null) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(dto.getRoleId());
        roleEntity.setRoleName(dto.getRoleName());

        return roleEntity;
    }

    public List<RoleDTO> toDTOs(List<RoleEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(this::toDTO).toList();
    }

    public List<RoleEntity> toEntities(List<RoleDTO> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream().map(this::toEntity).toList();
    }

}
