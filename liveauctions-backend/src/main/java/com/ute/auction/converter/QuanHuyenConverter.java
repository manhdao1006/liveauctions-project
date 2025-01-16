package com.ute.auction.converter;

import org.mapstruct.Mapper;

import com.ute.auction.dto.QuanHuyenDTO;
import com.ute.auction.entity.QuanHuyenEntity;

@Mapper(componentModel = "spring")
public interface QuanHuyenConverter {
    QuanHuyenDTO toDTO(QuanHuyenEntity entity);
}
