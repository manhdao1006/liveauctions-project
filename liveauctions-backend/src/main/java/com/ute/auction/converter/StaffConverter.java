package com.ute.auction.converter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.StaffDTO;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.entity.StaffEntity;
import com.ute.auction.entity.UserEntity;

@Component
public class StaffConverter {

    private final UserConverter userConverter;

    public StaffConverter(@Lazy UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public StaffDTO toDTO(StaffEntity entity) {
        if (entity == null) {
            return null;
        }

        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setStaffId(entity.getStaffId());
        staffDTO.setPosition(entity.getPosition());
        staffDTO.setDelFlag(entity.getDelFlag());
        staffDTO.setUser(toUserDTO(entity.getUser()));

        return staffDTO;
    }

    public StaffEntity toEntity(StaffDTO dto) {
        if (dto == null) {
            return null;
        }

        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setStaffId(dto.getStaffId());
        staffEntity.setPosition(dto.getPosition());
        staffEntity.setDelFlag("1");
        staffEntity.setUser(toUserEntity(dto.getUser()));

        return staffEntity;
    }

    private UserEntity toUserEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return userConverter.toEntity(userDTO);
    }

    private UserDTO toUserDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return userConverter.toDTO(userEntity);
    }

}
