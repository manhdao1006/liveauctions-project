package com.ute.auction.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.AnhSanPhamDTO;
import com.ute.auction.entity.AnhSanPhamEntity;

@Mapper(componentModel = "spring", uses = {
        SanPhamConverter.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnhSanPhamConverter {

    AnhSanPhamConverter INSTANCE = Mappers.getMapper(AnhSanPhamConverter.class);

    @Mapping(target = "maSanPham", source = "sanPham.maSanPham")
    AnhSanPhamDTO toDTO(AnhSanPhamEntity entity);

    @Mapping(target = "maSanPham", source = "sanPham.maSanPham")
    List<AnhSanPhamDTO> toDTOs(List<AnhSanPhamEntity> entity);

    AnhSanPhamEntity toEntity(AnhSanPhamDTO dto);

    @Mapping(target = "maAnhSanPham", ignore = true)
    AnhSanPhamEntity toEntity(AnhSanPhamDTO anhSanPhamDTO, @MappingTarget AnhSanPhamEntity updatedAnh);
}
