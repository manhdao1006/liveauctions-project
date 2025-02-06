package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.NguoiMuaDTO;
import com.ute.auction.entity.NguoiMuaEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NguoiMuaConverter {

    NguoiMuaConverter INSTANCE = Mappers.getMapper(NguoiMuaConverter.class);

    NguoiMuaDTO toDTO(NguoiMuaEntity entity);

    @Mapping(target = "trangThaiXoa", defaultValue = "1")
    @Mapping(target = "ngayDangKy", expression = "java(java.time.LocalDate.now())")
    NguoiMuaEntity toEntity(NguoiMuaDTO dto);

    @Mapping(target = "maNguoiMua", ignore = true)
    NguoiMuaEntity toEntity(NguoiMuaDTO nguoiMuaDTO, @MappingTarget NguoiMuaEntity updatedNguoiMua);
}
