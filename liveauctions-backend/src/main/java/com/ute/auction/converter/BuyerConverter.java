package com.ute.auction.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.BuyerDTO;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.entity.BuyerEntity;
import com.ute.auction.entity.UserEntity;

@Component
public class BuyerConverter {

    private UserConverter userConverter;

    @Autowired
    public void setUserConverter(@Lazy UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public BuyerDTO toDTO(BuyerEntity entity) {
        BuyerDTO buyerDTO = new BuyerDTO();
        buyerDTO.setBuyerId(entity.getBuyerId());
        buyerDTO.setRegistrationDate(entity.getRegistrationDate());
        buyerDTO.setUser(toUserDTO(entity.getUser()));

        return buyerDTO;
    }

    public BuyerEntity toEntity(BuyerDTO dto) {
        BuyerEntity buyerEntity = new BuyerEntity();
        buyerEntity.setBuyerId(dto.getBuyerId());
        buyerEntity.setRegistrationDate(dto.getRegistrationDate());
        buyerEntity.setUser(toUserEntity(dto.getUser()));

        return buyerEntity;
    }

    private UserDTO toUserDTO(UserEntity userEntity) {
        return userConverter.toDTO(userEntity);
    }

    private UserEntity toUserEntity(UserDTO userDTO) {
        return userConverter.toEntity(userDTO);
    }
    
}
