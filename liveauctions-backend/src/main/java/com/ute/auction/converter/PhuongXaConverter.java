package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.PhuongXaDTO;
import com.ute.auction.entity.PhuongXaEntity;

@Mapper(componentModel = "spring", uses = {
        QuanHuyenConverter.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PhuongXaConverter {

    PhuongXaConverter INSTANCE = Mappers.getMapper(PhuongXaConverter.class);

    @Mapping(target = "quanHuyen.maQuanHuyen", source = "quanHuyen.maQuanHuyen")
    PhuongXaDTO toDTO(PhuongXaEntity entity);

    PhuongXaEntity toEntity(PhuongXaDTO dto);

    @Mapping(target = "maPhuongXa", ignore = true)
    PhuongXaEntity toEntity(PhuongXaDTO phuongXaDTO, @MappingTarget PhuongXaEntity updatedPhuongXa);

}
