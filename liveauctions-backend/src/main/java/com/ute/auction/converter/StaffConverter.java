package com.ute.auction.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.StaffDTO;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.entity.StaffEntity;
import com.ute.auction.entity.UserEntity;

@Component
public class StaffConverter {

    private UserConverter userConverter;

    @Autowired
    public void setUserConverter(@Lazy UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public StaffDTO toDTO(StaffEntity entity) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setStaffId(entity.getStaffId());
        staffDTO.setPosition(entity.getPosition());
        staffDTO.setUser(toUserDTO(entity.getUser()));

        return staffDTO;
    }

    public StaffEntity toEntity(StaffDTO dto) {
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setStaffId(dto.getStaffId());
        staffEntity.setPosition(dto.getPosition());
        staffEntity.setUser(toUserEntity(dto.getUser()));

        return staffEntity;
    }

    private UserEntity toUserEntity(UserDTO userDTO) {
        return userConverter.toEntity(userDTO);
    }

    private UserDTO toUserDTO(UserEntity userEntity) {
        return userConverter.toDTO(userEntity);
    }
    
}
