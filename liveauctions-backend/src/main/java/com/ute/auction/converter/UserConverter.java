package com.ute.auction.converter;

import java.util.List;

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

    private final RoleConverter roleConverter;
    private final CityConverter cityConverter;

    public UserConverter(@Lazy RoleConverter roleConverter, @Lazy CityConverter cityConverter) {
        this.roleConverter = roleConverter;
        this.cityConverter = cityConverter;
    }

    public UserDTO toDTO(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(entity.getUserId());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setLastName(entity.getLastName());
        userDTO.setEmail(entity.getEmail());
        userDTO.setPassword(entity.getPassword());
        userDTO.setPhoneNumber(entity.getPhoneNumber());
        userDTO.setAddress(entity.getAddress());
        userDTO.setStatus(entity.getStatus());
        userDTO.setDelFlag(entity.getDelFlag());
        userDTO.setAvatar(entity.getAvatar() != null ? entity.getAvatar() : null);
        userDTO.setDob(entity.getDob());
        if (entity.getGender().isEmpty()) {
            userDTO.setGender("Male");
        } else if (entity.getGender().contains("M")) {
            userDTO.setGender("Male");
        } else if (entity.getGender().contains("F")) {
            userDTO.setGender("Female");
        } else if (entity.getGender().contains("L")) {
            userDTO.setGender("Other");
        }
        userDTO.setRoles(entity.getRoles() != null ? toRolesDTO(entity.getRoles()) : null);
        userDTO.setCity(entity.getCity() != null ? toCityDTO(entity.getCity()) : null);

        return userDTO;
    }

    public UserEntity toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(dto.getUserId());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setAddress(dto.getAddress());
        userEntity.setStatus("Active");
        userEntity.setDelFlag("1");
        userEntity.setAvatar(dto.getAvatar() != null ? dto.getAvatar() : null);
        userEntity.setDob(dto.getDob());
        if (dto.getGender() == null || dto.getGender().toUpperCase().equals("Male".toUpperCase())) {
            userEntity.setGender("M");
        } else if (dto.getGender().toUpperCase().equals("Female".toUpperCase())) {
            userEntity.setGender("F");
        } else if (dto.getGender().toUpperCase().equals("Other".toUpperCase())) {
            userEntity.setGender("L");
        }
        userEntity.setRoles(dto.getRoles() != null ? toRolesEntity(dto.getRoles()) : null);
        userEntity.setCity(dto.getCity() != null ? toCityEntity(dto.getCity()) : null);

        return userEntity;
    }

    public UserEntity toEntity(UserDTO dto, UserEntity updatedUser) {
        if (dto == null) {
            return null;
        }

        updatedUser.setFirstName(dto.getFirstName() != null ? dto.getFirstName() : updatedUser.getFirstName());
        updatedUser.setLastName(dto.getLastName() != null ? dto.getLastName() : updatedUser.getLastName());
        updatedUser.setEmail(dto.getEmail() != null ? dto.getEmail() : updatedUser.getEmail());
        updatedUser.setPhoneNumber(dto.getPhoneNumber() != null ? dto.getPhoneNumber() : updatedUser.getPhoneNumber());
        updatedUser.setAddress(dto.getAddress() != null ? dto.getAddress() : updatedUser.getAddress());
        updatedUser.setDob(dto.getDob() != null ? dto.getDob() : updatedUser.getDob());
        if (dto.getGender() == null) {
            updatedUser.setGender(updatedUser.getGender());
        } else if (dto.getGender().toUpperCase().equals("Male".toUpperCase())) {
            updatedUser.setGender("M");
        } else if (dto.getGender().toUpperCase().equals("Female".toUpperCase())) {
            updatedUser.setGender("F");
        } else if (dto.getGender().toUpperCase().equals("Other".toUpperCase())) {
            updatedUser.setGender("L");
        }

        return updatedUser;
    }

    private CityEntity toCityEntity(CityDTO cityDTO) {
        if (cityDTO == null) {
            return null;
        }
        return cityConverter.toEntity(cityDTO);
    }

    private List<RoleEntity> toRolesEntity(List<RoleDTO> roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        return roleConverter.toEntities(roleDTO);
    }

    private CityDTO toCityDTO(CityEntity cityEntity) {
        if (cityEntity == null) {
            return null;
        }
        return cityConverter.toDTO(cityEntity);
    }

    private List<RoleDTO> toRolesDTO(List<RoleEntity> roleEntity) {
        if (roleEntity == null) {
            return null;
        }
        return roleConverter.toDTOs(roleEntity);
    }

}
