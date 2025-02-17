package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.QuanHuyenConverter;
import com.ute.auction.dto.QuanHuyenDTO;
import com.ute.auction.entity.QuanHuyenEntity;
import com.ute.auction.repository.QuanHuyenRepository;
import com.ute.auction.service.IQuanHuyenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuanHuyenService implements IQuanHuyenService {

    private final QuanHuyenRepository quanHuyenRepository;
    private final QuanHuyenConverter quanHuyenConverter;

    @Override
    public List<QuanHuyenDTO> getQuanHuyens() {
        List<QuanHuyenEntity> entities = quanHuyenRepository.findAll();
        return entities.stream().map(quanHuyenConverter::toDTO).collect(Collectors.toList());
    }

}
