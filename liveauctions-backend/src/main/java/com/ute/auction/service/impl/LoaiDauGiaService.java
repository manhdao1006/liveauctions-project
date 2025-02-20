package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.LoaiDauGiaConverter;
import com.ute.auction.dto.LoaiDauGiaDTO;
import com.ute.auction.entity.LoaiDauGiaEntity;
import com.ute.auction.repository.LoaiDauGiaRepository;
import com.ute.auction.service.ILoaiDauGiaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoaiDauGiaService implements ILoaiDauGiaService {

    private final LoaiDauGiaConverter loaiDauGiaConverter;
    private final LoaiDauGiaRepository loaiDauGiaRepository;

    @Override
    public List<LoaiDauGiaDTO> getLoaiDauGias() {
        List<LoaiDauGiaEntity> entities = loaiDauGiaRepository.findAll();
        return entities.stream().map(loaiDauGiaConverter::toDTO).collect(Collectors.toList());
    }

}
