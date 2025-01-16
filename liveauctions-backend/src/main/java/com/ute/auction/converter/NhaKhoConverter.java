package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.NhaKhoDTO;
import com.ute.auction.entity.NhaKhoEntity;

@Mapper(componentModel = "spring", uses = {
        PhuongXaConverter.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NhaKhoConverter {

    NhaKhoConverter INSTANCE = Mappers.getMapper(NhaKhoConverter.class);

    @Mapping(target = "phuongXa.maPhuongXa", source = "phuongXa.maPhuongXa")
    NhaKhoDTO toDTO(NhaKhoEntity entity);

    NhaKhoEntity toEntity(NhaKhoDTO dto);

    @Mapping(target = "maNhaKho", ignore = true)
    NhaKhoEntity toEntity(NhaKhoDTO nhaKhoDTO, @MappingTarget NhaKhoEntity updatedNhaKho);
}
