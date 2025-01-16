package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.SanPhamDangKyDTO;
import com.ute.auction.entity.SanPhamDangKyEntity;

@Mapper(componentModel = "spring", uses = { NguoiBanConverter.class, DanhMucConConverter.class,
                LoaiDauGiaConverter.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SanPhamDangKyConverter {

        SanPhamDangKyConverter INSTANCE = Mappers.getMapper(SanPhamDangKyConverter.class);

        @Mapping(target = "maNguoiBan", source = "nguoiBan.maNguoiBan")
        @Mapping(target = "maDanhMucCon", source = "danhMucCon.maDanhMucCon")
        @Mapping(target = "maLoaiDauGia", source = "loaiDauGia.maLoaiDauGia")
        SanPhamDangKyDTO toDTO(SanPhamDangKyEntity entity);

        @Mapping(target = "trangThaiXoa", defaultValue = "1")
        SanPhamDangKyEntity toEntity(SanPhamDangKyDTO dto);

        @Mapping(target = "maSanPhamDangKy", ignore = true)
        SanPhamDangKyEntity toEntity(SanPhamDangKyDTO sanPhamDangKyDTO,
                        @MappingTarget SanPhamDangKyEntity updatedSanPhamDangKy);
}
