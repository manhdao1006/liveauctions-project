package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.LichSuDauGiaDTO;
import com.ute.auction.entity.LichSuDauGiaEntity;

@Mapper(componentModel = "spring", uses = { SanPhamConverter.class, PhienDauGiaConverter.class,
        NguoiMuaConverter.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LichSuDauGiaConverter {

    LichSuDauGiaConverter INSTANCE = Mappers.getMapper(LichSuDauGiaConverter.class);

    @Mapping(target = "sanPham.maSanPham", source = "sanPham.maSanPham")
    @Mapping(target = "phienDauGia.maPhienDauGia", source = "phienDauGia.maPhienDauGia")
    @Mapping(target = "nguoiMua.maNguoiMua", source = "nguoiMua.maNguoiMua")
    LichSuDauGiaDTO toDTO(LichSuDauGiaEntity entity);

    LichSuDauGiaEntity toEntity(LichSuDauGiaDTO dto);

    @Mapping(target = "maLichSuDauGia", ignore = true)
    LichSuDauGiaEntity toEntity(LichSuDauGiaDTO lichSuDauGiaDTO, @MappingTarget LichSuDauGiaEntity updatedLichSuDauGia);
}
