package com.ute.auction.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ute.auction.dto.NhaThamDinhDTO;
import com.ute.auction.entity.NhaThamDinhEntity;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NhaThamDinhConverter {

    NhaThamDinhConverter INSTANCE = Mappers.getMapper(NhaThamDinhConverter.class);

    NhaThamDinhDTO toDTO(NhaThamDinhEntity entity);

    @Mapping(target = "trangThaiXoa", defaultValue = "1")
    NhaThamDinhEntity toEntity(NhaThamDinhDTO dto);

    @Mapping(target = "maNhaThamDinh", ignore = true)
    NhaThamDinhEntity toEntity(NhaThamDinhDTO nhaThamDinhDTO, @MappingTarget NhaThamDinhEntity updatedNhaThamDinh);
}
