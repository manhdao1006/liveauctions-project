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

    @Mapping(target = "maSanPham", source = "sanPham.maSanPham")
    @Mapping(target = "maPhienDauGia", source = "phienDauGia.maPhienDauGia")
    @Mapping(target = "maNguoiMua", source = "nguoiMua.maNguoiMua")
    @Mapping(target = "maChiPhi", source = "chiPhi.maChiPhi")
    LichSuDauGiaDTO toDTO(LichSuDauGiaEntity entity);

    LichSuDauGiaEntity toEntity(LichSuDauGiaDTO dto);

    @Mapping(target = "maLichSuDauGia", ignore = true)
    @Mapping(target = "thoiGianDauGia", ignore = true)
    LichSuDauGiaEntity toEntity(LichSuDauGiaDTO lichSuDauGiaDTO, @MappingTarget LichSuDauGiaEntity updatedLichSuDauGia);
}
