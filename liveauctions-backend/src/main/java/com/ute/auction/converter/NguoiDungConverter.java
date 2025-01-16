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

    @Mapping(target = "vaiTros", source = "vaiTros")
    @Mapping(target = "phuongXa.maPhuongXa", source = "phuongXa.maPhuongXa")
    @Mapping(target = "nguoiMua.maNguoiMua", source = "nguoiMua.maNguoiMua")
    @Mapping(target = "nguoiBan.maNguoiBan", source = "nguoiBan.maNguoiBan")
    @Mapping(target = "nhanVien.maNhanVien", source = "nhanVien.maNhanVien")
    NguoiDungDTO toDTO(NguoiDungEntity entity);

    NguoiDungEntity toEntity(NguoiDungDTO dto);

    @Mapping(target = "maNguoiDung", ignore = true)
    NguoiDungEntity toEntity(NguoiDungDTO nguoiDungDTO, @MappingTarget NguoiDungEntity updatedNguoiDung);
}
