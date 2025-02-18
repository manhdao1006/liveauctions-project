package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.DanhMucConverter;
import com.ute.auction.dto.DanhMucDTO;
import com.ute.auction.entity.DanhMucEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.DanhMucRepository;
import com.ute.auction.service.IDanhMucService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DanhMucService implements IDanhMucService {

    private final DanhMucRepository danhMucRepository;
    private final DanhMucConverter danhMucConverter;

    @Override
    public List<DanhMucDTO> getDanhMucs() {
        List<DanhMucEntity> entities = danhMucRepository.findDanhMucsByTrangThaiXoa("1");
        return entities.stream().map(danhMucConverter::toDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public DanhMucDTO addDanhMuc(DanhMucDTO danhMucDTO) {
        DanhMucEntity danhMucEntity = danhMucConverter.toEntity(danhMucDTO);
        return danhMucConverter.toDTO(danhMucRepository.save(danhMucEntity));
    }

    @Transactional
    @Override
    public DanhMucDTO updateDanhMuc(long maDanhMuc, DanhMucDTO updatedDanhMuc) {
        DanhMucEntity danhMucEntity = danhMucRepository.findOneByMaDanhMuc(maDanhMuc)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục nào với mã danh mục là " + maDanhMuc));
        DanhMucEntity danhMucUpdated = danhMucConverter.toEntity(updatedDanhMuc, danhMucEntity);
        return danhMucConverter.toDTO(danhMucRepository.save(danhMucUpdated));
    }

    @Transactional
    @Override
    public void deleteDanhMuc(long maDanhMuc) {
        DanhMucEntity danhMucEntity = danhMucRepository.findOneByMaDanhMuc(maDanhMuc)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục nào với mã danh mục là " + maDanhMuc));
        danhMucEntity.setTrangThaiXoa("0");
        danhMucRepository.save(danhMucEntity);
    }

    @Override
    public DanhMucDTO getDanhMucByMaDanhMuc(long maDanhMuc) {
        DanhMucEntity danhMucEntity = danhMucRepository.findOneByMaDanhMuc(maDanhMuc)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục nào với mã danh mục là " + maDanhMuc));
        return danhMucConverter.toDTO(danhMucEntity);
    }

}
