package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.entity.NguoiDungEntity;

@Mapper(componentModel = "spring", uses = { VaiTroConverter.class, PhuongXaConverter.class, NguoiMuaConverter.class,
        NguoiBanConverter.class,
        NhanVienConverter.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NguoiDungConverter {

    NguoiDungConverter INSTANCE = Mappers.getMapper(NguoiDungConverter.class);

    @Mapping(target = "vaiTros", ignore = true)
    @Mapping(target = "maPhuongXa", source = "phuongXa.maPhuongXa")
    @Mapping(target = "maNguoiMua", source = "nguoiMua.maNguoiMua")
    @Mapping(target = "maNguoiBan", source = "nguoiBan.maNguoiBan")
    @Mapping(target = "maNhanVien", source = "nhanVien.maNhanVien")
    NguoiDungDTO toDTO(NguoiDungEntity entity);

    @Mapping(target = "trangThaiXoa", defaultValue = "1")
    @Mapping(target = "trangThaiHoatDong", defaultValue = "Hoạt động")
    NguoiDungEntity toEntity(NguoiDungDTO dto);

    @Mapping(target = "maNguoiDung", ignore = true)
    NguoiDungEntity toEntity(NguoiDungDTO nguoiDungDTO, @MappingTarget NguoiDungEntity updatedNguoiDung);
}
