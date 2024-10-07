package com.ute.auction.converter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ute.auction.dto.SellerDTO;
import com.ute.auction.dto.UserDTO;
import com.ute.auction.entity.SellerEntity;
import com.ute.auction.entity.UserEntity;

@Component
public class SellerConverter {

    private final UserConverter userConverter;

    public SellerConverter(@Lazy UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public SellerDTO toDTO(SellerEntity entity) {
        if (entity == null) {
            return null;
        }

        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setSellerId(entity.getSellerId());
        sellerDTO.setTaxCode(entity.getTaxCode());
        sellerDTO.setRegistrationDate(entity.getRegistrationDate());
        sellerDTO.setDelFlag(entity.getDelFlag());
        sellerDTO.setUser(toUserDTO(entity.getUser()));

        return sellerDTO;
    }

    public SellerEntity toEntity(SellerDTO dto) {
        if (dto == null) {
            return null;
        }

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setSellerId(dto.getSellerId());
        sellerEntity.setTaxCode(dto.getTaxCode());
        sellerEntity.setRegistrationDate(dto.getRegistrationDate());
        sellerEntity.setDelFlag("1");
        sellerEntity.setUser(toUserEntity(dto.getUser()));

        return sellerEntity;
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
