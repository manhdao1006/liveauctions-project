package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.DanhMucDTO;
import com.ute.auction.entity.DanhMucEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DanhMucConverter {

    DanhMucConverter INSTANCE = Mappers.getMapper(DanhMucConverter.class);

    DanhMucDTO toDTO(DanhMucEntity entity);

    @Mapping(target = "trangThaiXoa", defaultValue = "1")
    DanhMucEntity toEntity(DanhMucDTO dto);

    @Mapping(target = "maDanhMuc", ignore = true)
    DanhMucEntity toEntity(DanhMucDTO danhMucDTO, @MappingTarget DanhMucEntity updatedDanhMuc);
}
