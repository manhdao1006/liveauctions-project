package com.ute.auction.converter;

import org.mapstruct.Mapper;

import com.ute.auction.dto.LoaiDauGiaDTO;
import com.ute.auction.entity.LoaiDauGiaEntity;

@Mapper(componentModel = "spring")
public interface LoaiDauGiaConverter {
    LoaiDauGiaDTO toDTO(LoaiDauGiaEntity entity);
}
