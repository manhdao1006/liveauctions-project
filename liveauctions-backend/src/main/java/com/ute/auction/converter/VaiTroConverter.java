package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ute.auction.dto.VaiTroDTO;
import com.ute.auction.entity.VaiTroEntity;

@Mapper(componentModel = "spring")
public interface VaiTroConverter {
    @Mapping(target = "nguoiDungs", ignore = true)
    VaiTroDTO toDTO(VaiTroEntity entity);
}
