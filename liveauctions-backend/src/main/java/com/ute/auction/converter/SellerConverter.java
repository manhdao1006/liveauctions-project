package com.ute.auction.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.SellerDTO;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.entity.SellerEntity;
import com.ute.auction.entity.UserEntity;

@Component
public class SellerConverter {

    private UserConverter userConverter;

    @Autowired
    public void setUserConverter(@Lazy UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public SellerDTO toDTO(SellerEntity entity) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setSellerId(entity.getSellerId());
        sellerDTO.setTaxCode(entity.getTaxCode());
        sellerDTO.setRegistrationDate(entity.getRegistrationDate());
        sellerDTO.setUser(toUserDTO(entity.getUser()));

        return sellerDTO;
    }

    public SellerEntity toEntity(SellerDTO dto) {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setSellerId(dto.getSellerId());
        sellerEntity.setTaxCode(dto.getTaxCode());
        sellerEntity.setRegistrationDate(dto.getRegistrationDate());
        sellerEntity.setUser(toUserEntity(dto.getUser()));

        return sellerEntity;
    }

    private UserDTO toUserDTO(UserEntity userEntity) {
        return userConverter.toDTO(userEntity);
    }

    private UserEntity toUserEntity(UserDTO userDTO) {
        return userConverter.toEntity(userDTO);
    }
    
}
