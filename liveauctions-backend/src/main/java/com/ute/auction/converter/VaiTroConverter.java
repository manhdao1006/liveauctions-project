package com.ute.auction.converter;

import org.mapstruct.Mapper;

import com.ute.auction.dto.VaiTroDTO;
import com.ute.auction.entity.VaiTroEntity;

@Mapper(componentModel = "spring")
public interface VaiTroConverter {
    VaiTroDTO toDTO(VaiTroEntity entity);
}
