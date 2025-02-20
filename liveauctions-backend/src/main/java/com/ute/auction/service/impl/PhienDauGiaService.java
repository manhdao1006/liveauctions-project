package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.PhienDauGiaConverter;
import com.ute.auction.dto.PhienDauGiaDTO;
import com.ute.auction.entity.LoaiDauGiaEntity;
import com.ute.auction.entity.NhanVienEntity;
import com.ute.auction.entity.PhienDauGiaEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.LoaiDauGiaRepository;
import com.ute.auction.repository.NhanVienRepository;
import com.ute.auction.repository.PhienDauGiaRepository;
import com.ute.auction.service.IPhienDauGiaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhienDauGiaService implements IPhienDauGiaService {

    private final PhienDauGiaConverter phienDauGiaConverter;
    private final PhienDauGiaRepository phienDauGiaRepository;
    private final NhanVienRepository nhanVienRepository;
    private final LoaiDauGiaRepository loaiDauGiaRepository;

    @Override
    public List<PhienDauGiaDTO> getPhienDauGias() {
        List<PhienDauGiaEntity> entites = phienDauGiaRepository.findPhienDauGiasByTrangThaiXoa("1");
        return entites.stream().map(phienDauGiaConverter::toDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PhienDauGiaDTO addPhienDauGia(PhienDauGiaDTO phienDauGiaDTO) {
        NhanVienEntity nhanVienEntity = nhanVienRepository.findOneByMaNhanVien(phienDauGiaDTO.getMaNhanVien())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy nhân viên nào với mã nhân viên là " + phienDauGiaDTO.getMaNhanVien()));
        LoaiDauGiaEntity loaiDauGiaEntity = loaiDauGiaRepository.findOneByMaLoaiDauGia(phienDauGiaDTO.getMaLoaiDauGia())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy loại đấu giá nào với mã loại đấu giá là " + phienDauGiaDTO.getMaLoaiDauGia()));
        PhienDauGiaEntity phienDauGiaEntity = phienDauGiaConverter.toEntity(phienDauGiaDTO);
        phienDauGiaEntity.setNhanVien(nhanVienEntity);
        phienDauGiaEntity.setLoaiDauGia(loaiDauGiaEntity);
        return phienDauGiaConverter.toDTO(phienDauGiaRepository.save(phienDauGiaEntity));
    }

    @Transactional
    @Override
    public PhienDauGiaDTO updatePhienDauGia(long maPhienDauGia, PhienDauGiaDTO updatedPhienDauGia) {
        PhienDauGiaEntity phienDauGiaEntity = phienDauGiaRepository.findOneByMaPhienDauGia(maPhienDauGia)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy phiên đấu giá nào với mã phiên đấu giá là " + maPhienDauGia));

        if (updatedPhienDauGia.getMaNhanVien() != null) {
            NhanVienEntity nhanVienEntity = nhanVienRepository.findOneByMaNhanVien(updatedPhienDauGia.getMaNhanVien())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy nhân viên nào với mã nhân viên là " + updatedPhienDauGia.getMaNhanVien()));
            phienDauGiaEntity.setNhanVien(nhanVienEntity);
        }
        if (updatedPhienDauGia.getMaLoaiDauGia() != null) {
            LoaiDauGiaEntity loaiDauGiaEntity = loaiDauGiaRepository
                    .findOneByMaLoaiDauGia(updatedPhienDauGia.getMaLoaiDauGia())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy loại đấu giá nào với mã loại đấu giá là "
                                    + updatedPhienDauGia.getMaLoaiDauGia()));
            phienDauGiaEntity.setLoaiDauGia(loaiDauGiaEntity);
        }
        PhienDauGiaEntity phienDauGiaUpdated = phienDauGiaConverter.toEntity(updatedPhienDauGia, phienDauGiaEntity);
        return phienDauGiaConverter.toDTO(phienDauGiaRepository.save(phienDauGiaUpdated));
    }

    @Transactional
    @Override
    public void deletePhienDauGia(long maPhienDauGia) {
        PhienDauGiaEntity phienDauGiaEntity = phienDauGiaRepository.findOneByMaPhienDauGia(maPhienDauGia)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy phiên đấu giá nào với mã phiên đấu giá là " + maPhienDauGia));
        phienDauGiaEntity.setTrangThaiXoa("0");
        phienDauGiaRepository.save(phienDauGiaEntity);
    }

    @Override
    public PhienDauGiaDTO getPhienDauGiaByMaPhienDauGia(long maPhienDauGia) {
        PhienDauGiaEntity phienDauGiaEntity = phienDauGiaRepository.findOneByMaPhienDauGia(maPhienDauGia)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy phiên đấu giá nào với mã phiên đấu giá là " + maPhienDauGia));
        return phienDauGiaConverter.toDTO(phienDauGiaEntity);
    }

}
