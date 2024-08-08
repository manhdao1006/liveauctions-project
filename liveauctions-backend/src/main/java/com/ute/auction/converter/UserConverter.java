package com.ute.auction.converter;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.CityDTO;
import com.ute.auction.dto.RoleDTO;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.entity.CityEntity;
import com.ute.auction.entity.RoleEntity;
import com.ute.auction.entity.UserEntity;

@Component
public class UserConverter {

    private RoleConverter roleConverter;
    private CityConverter cityConverter;

    @Autowired
    public void setRoleConverter(@Lazy RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Autowired
    public void setCityConverter(@Lazy CityConverter cityConverter) {
        this.cityConverter = cityConverter;
    }

    public UserDTO toDTO(UserEntity entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setLastName(entity.getLastName());
        userDTO.setEmail(entity.getEmail());
        userDTO.setPassword(entity.getPassword());
        userDTO.setPhoneNumber(entity.getPhoneNumber());
        userDTO.setAddress(entity.getAddress());
        userDTO.setStatus(entity.getStatus());
        userDTO.setAvatar(entity.getAvatar() != null ? Base64.getEncoder().encodeToString(entity.getAvatar()) : null);
        userDTO.setDob(entity.getDob());
        if (entity.getGender().contains("F")) {
            userDTO.setGender("Female");
        } else if (entity.getGender().contains("M")) {
            userDTO.setGender("Male");
        } else if (entity.getGender().contains("L")) {
            userDTO.setGender("Other");
        }
        userDTO.setRoles(toRolesDTO(entity.getRoles()));
        userDTO.setCity(toCityDTO(entity.getCity()));

        return userDTO;
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(dto.getId());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPassword(dto.getPassword());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setAddress(dto.getAddress());
        userEntity.setStatus(dto.getStatus());
        userEntity.setAvatar(dto.getAvatar() != null ? Base64.getDecoder().decode(dto.getAvatar()) : null);
        userEntity.setDob(dto.getDob());
        if (dto.getGender() == null || dto.getGender().toUpperCase().equals("Male".toUpperCase())) {
            userEntity.setGender("M");
        } else if (dto.getGender().toUpperCase().equals("Female".toUpperCase())) {
            userEntity.setGender("F");
        } else if (dto.getGender().toUpperCase().equals("Other".toUpperCase())) {
            userEntity.setGender("L");
        }
        userEntity.setRoles(toRolesEntity(dto.getRoles()));
        userEntity.setCity(toCityEntity(dto.getCity()));

        return userEntity;
    }

    private CityEntity toCityEntity(CityDTO cityDTO) {
        return cityConverter.toEntity(cityDTO);
    }

    private List<RoleEntity> toRolesEntity(List<RoleDTO> roleDTO) {
        return roleConverter.toEntities(roleDTO);
    }

    private CityDTO toCityDTO(CityEntity cityEntity) {
        return cityConverter.toDTO(cityEntity);
    }

    private List<RoleDTO> toRolesDTO(List<RoleEntity> roleEntity) {
        return roleConverter.toDTOs(roleEntity);
    }
    
}
