package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.NhanVienDTO;
import com.ute.auction.entity.NhanVienEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NhanVienConverter {

    NhanVienConverter INSTANCE = Mappers.getMapper(NhanVienConverter.class);

    NhanVienDTO toDTO(NhanVienEntity entity);

    @Mapping(target = "trangThaiXoa", defaultValue = "1")
    NhanVienEntity toEntity(NhanVienDTO dto);

    @Mapping(target = "maNhanVien", ignore = true)
    NhanVienEntity toEntity(NhanVienDTO nguoiMuaDTO, @MappingTarget NhanVienEntity updatedNguoiMua);
}
