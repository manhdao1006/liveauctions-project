package com.ute.auction.converter;

import org.mapstruct.Mapper;

import com.ute.auction.dto.DanhMucDTO;
import com.ute.auction.entity.DanhMucEntity;

@Mapper(componentModel = "spring")
public interface DanhMucConverter {
    DanhMucDTO toDTO(DanhMucEntity entity);
}
