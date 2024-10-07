package com.ute.auction.converter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.BuyerDTO;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.entity.BuyerEntity;
import com.ute.auction.entity.UserEntity;

@Component
public class BuyerConverter {

    private final UserConverter userConverter;

    public BuyerConverter(@Lazy UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public BuyerDTO toDTO(BuyerEntity entity) {
        if (entity == null) {
            return null;
        }

        BuyerDTO buyerDTO = new BuyerDTO();
        buyerDTO.setBuyerId(entity.getBuyerId());
        buyerDTO.setRegistrationDate(entity.getRegistrationDate());
        buyerDTO.setDelFlag(entity.getDelFlag());
        buyerDTO.setUser(toUserDTO(entity.getUser()));

        return buyerDTO;
    }

    public BuyerEntity toEntity(BuyerDTO dto) {
        if (dto == null) {
            return null;
        }

        BuyerEntity buyerEntity = new BuyerEntity();
        buyerEntity.setBuyerId(dto.getBuyerId());
        buyerEntity.setRegistrationDate(dto.getRegistrationDate());
        buyerEntity.setDelFlag("1");
        buyerEntity.setUser(toUserEntity(dto.getUser()));

        return buyerEntity;
    }

    private UserDTO toUserDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return userConverter.toDTO(userEntity);
    }

    private UserEntity toUserEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return userConverter.toEntity(userDTO);
    }

}
