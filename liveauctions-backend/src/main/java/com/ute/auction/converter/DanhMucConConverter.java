package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.DanhMucConDTO;
import com.ute.auction.entity.DanhMucConEntity;

@Mapper(componentModel = "spring", uses = {
        DanhMucConverter.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DanhMucConConverter {

    DanhMucConConverter INSTANCE = Mappers.getMapper(DanhMucConConverter.class);

    @Mapping(target = "danhMuc.maDanhMuc", source = "danhMuc.maDanhMuc")
    DanhMucConDTO toDTO(DanhMucConEntity entity);

    DanhMucConEntity toEntity(DanhMucConDTO dto);

    @Mapping(target = "maDanhMucCon", ignore = true)
    DanhMucConEntity toEntity(DanhMucConDTO danhMucConDTO, @MappingTarget DanhMucConEntity updatedDanhMucCon);
}
