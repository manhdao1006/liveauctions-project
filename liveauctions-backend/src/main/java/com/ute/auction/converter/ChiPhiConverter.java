package com.ute.auction.converter;

import org.mapstruct.Mapper;

import com.ute.auction.dto.ChiPhiDTO;
import com.ute.auction.entity.ChiPhiEntity;

@Mapper(componentModel = "spring")
public interface ChiPhiConverter {
    ChiPhiDTO toDTO(ChiPhiEntity entity);
}
