package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.entity.SanPhamEntity;

@Mapper(componentModel = "spring", uses = { NhaThamDinhConverter.class, NhaKhoConverter.class, NguoiBanConverter.class,
        DanhMucConConverter.class,
        LoaiDauGiaConverter.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SanPhamConverter {

    SanPhamConverter INSTANCE = Mappers.getMapper(SanPhamConverter.class);

    @Mapping(target = "nhaThamDinh.maNhaThamDinh", source = "nhaThamDinh.maNhaThamDinh")
    @Mapping(target = "nhaKho.maNhaKho", source = "nhaKho.maNhaKho")
    @Mapping(target = "nguoiBan.maNguoiBan", source = "nguoiBan.maNguoiBan")
    @Mapping(target = "danhMucCon.maDanhMucCon", source = "danhMucCon.maDanhMucCon")
    @Mapping(target = "loaiDauGia.maLoaiDauGia", source = "loaiDauGia.maLoaiDauGia")
    SanPhamDTO toDTO(SanPhamEntity entity);

    SanPhamEntity toEntity(SanPhamDTO dto);

    @Mapping(target = "maSanPham", ignore = true)
    SanPhamEntity toEntity(SanPhamDTO sanPhamDTO, @MappingTarget SanPhamEntity updatedSanPham);
}
