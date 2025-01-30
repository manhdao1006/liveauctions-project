package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.SanPhamDauGiaDTO;
import com.ute.auction.entity.SanPhamDauGiaEntity;

@Mapper(componentModel = "spring", uses = { PhienDauGiaConverter.class,
                SanPhamConverter.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SanPhamDauGiaConverter {

        SanPhamDauGiaConverter INSTANCE = Mappers.getMapper(SanPhamDauGiaConverter.class);

        @Mapping(target = "maPhienDauGia", source = "phienDauGia.maPhienDauGia")
        @Mapping(target = "maSanPham", source = "sanPham.maSanPham")
        SanPhamDauGiaDTO toDTO(SanPhamDauGiaEntity entity);

        SanPhamDauGiaEntity toEntity(SanPhamDauGiaDTO dto);

        @Mapping(target = "maSanPhamDauGia", ignore = true)
        SanPhamDauGiaEntity toEntity(SanPhamDauGiaDTO sanPhamDauGiaDTO,
                        @MappingTarget SanPhamDauGiaEntity updatedSanPhamDauGia);
}
