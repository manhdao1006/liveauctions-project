package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.NguoiBanDTO;
import com.ute.auction.entity.NguoiBanEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NguoiBanConverter {

    NguoiBanConverter INSTANCE = Mappers.getMapper(NguoiBanConverter.class);

    NguoiBanDTO toDTO(NguoiBanEntity entity);

    @Mapping(target = "trangThaiXoa", defaultValue = "1")
    NguoiBanEntity toEntity(NguoiBanDTO dto);

    @Mapping(target = "maNguoiBan", ignore = true)
    NguoiBanEntity toEntity(NguoiBanDTO nguoiBanDTO, @MappingTarget NguoiBanEntity updatedNguoiBan);
}
