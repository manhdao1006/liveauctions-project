package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.DanhMucConConverter;
import com.ute.auction.dto.DanhMucConDTO;
import com.ute.auction.entity.DanhMucConEntity;
import com.ute.auction.entity.DanhMucEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.DanhMucConRepository;
import com.ute.auction.repository.DanhMucRepository;
import com.ute.auction.service.IDanhMucConService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DanhMucConService implements IDanhMucConService {

    private final DanhMucRepository danhMucRepository;
    private final DanhMucConRepository danhMucConRepository;
    private final DanhMucConConverter danhMucConConverter;

    @Override
    public List<DanhMucConDTO> getDanhMucCons() {
        List<DanhMucConEntity> entities = danhMucConRepository.findDanhMucConsByTrangThaiXoa("1");
        return entities.stream().map(danhMucConConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<DanhMucConDTO> getDanhMucConsByMaDanhMuc(long maDanhMuc) {
        List<DanhMucConEntity> entities = danhMucConRepository.findDanhMucConsByTrangThaiXoaAndDanhMuc_MaDanhMuc("1",
                maDanhMuc);
        return entities.stream().map(danhMucConConverter::toDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public DanhMucConDTO addDanhMucCon(DanhMucConDTO danhMucConDTO) {
        DanhMucEntity danhMucEntity = danhMucRepository.findOneByMaDanhMuc(danhMucConDTO.getMaDanhMuc())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục nào với mã danh mục là " + danhMucConDTO.getMaDanhMuc()));
        DanhMucConEntity danhMucConEntity = danhMucConConverter.toEntity(danhMucConDTO);
        danhMucConEntity.setDanhMuc(danhMucEntity);
        return danhMucConConverter.toDTO(danhMucConRepository.save(danhMucConEntity));
    }

    @Transactional
    @Override
    public DanhMucConDTO updateDanhMucCon(long maDanhMucCon, DanhMucConDTO updatedDanhMucCon) {
        DanhMucConEntity danhMucConEntity = danhMucConRepository.findOneByMaDanhMucCon(maDanhMucCon)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục con nào với mã danh mục con là " + maDanhMucCon));
        if (updatedDanhMucCon.getMaDanhMuc() != null) {
            DanhMucEntity danhMucEntity = danhMucRepository.findById(updatedDanhMucCon.getMaDanhMuc())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy danh mục cha nào với mã danh mục là " + updatedDanhMucCon.getMaDanhMuc()));
            danhMucConEntity.setDanhMuc(danhMucEntity);
        }
        DanhMucConEntity danhMucConUpdated = danhMucConConverter.toEntity(updatedDanhMucCon, danhMucConEntity);
        return danhMucConConverter.toDTO(danhMucConRepository.save(danhMucConUpdated));
    }

    @Transactional
    @Override
    public void deleteDanhMucCon(long maDanhMucCon) {
        DanhMucConEntity danhMucConEntity = danhMucConRepository.findOneByMaDanhMucCon(maDanhMucCon)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục nào với mã danh mục là " + maDanhMucCon));
        danhMucConEntity.setTrangThaiXoa("0");
        danhMucConRepository.save(danhMucConEntity);
    }

    @Override
    public DanhMucConDTO getDanhMucConByMaDanhMucCon(long maDanhMucCon) {
        DanhMucConEntity danhMucConEntity = danhMucConRepository.findOneByMaDanhMucCon(maDanhMucCon)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục con nào với mã danh mục con là " + maDanhMucCon));
        return danhMucConConverter.toDTO(danhMucConEntity);
    }

}
