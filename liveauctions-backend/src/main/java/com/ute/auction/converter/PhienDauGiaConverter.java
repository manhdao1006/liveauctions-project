package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.PhienDauGiaDTO;
import com.ute.auction.entity.PhienDauGiaEntity;

@Mapper(componentModel = "spring", uses = { NhanVienConverter.class,
        LoaiDauGiaConverter.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PhienDauGiaConverter {

    PhienDauGiaConverter INSTANCE = Mappers.getMapper(PhienDauGiaConverter.class);

    @Mapping(target = "maNhanVien", source = "nhanVien.maNhanVien")
    @Mapping(target = "maLoaiDauGia", source = "loaiDauGia.maLoaiDauGia")
    PhienDauGiaDTO toDTO(PhienDauGiaEntity entity);

    @Mapping(target = "trangThaiXoa", defaultValue = "1")
    PhienDauGiaEntity toEntity(PhienDauGiaDTO dto);

    @Mapping(target = "maPhienDauGia", ignore = true)
    PhienDauGiaEntity toEntity(PhienDauGiaDTO phienDauGiaDTO, @MappingTarget PhienDauGiaEntity updatedPhienDauGia);
}
