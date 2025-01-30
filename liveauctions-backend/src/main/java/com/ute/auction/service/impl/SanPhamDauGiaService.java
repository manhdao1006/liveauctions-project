package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.SanPhamDauGiaConverter;
import com.ute.auction.dto.SanPhamDauGiaDTO;
import com.ute.auction.entity.PhienDauGiaEntity;
import com.ute.auction.entity.SanPhamDauGiaEntity;
import com.ute.auction.entity.SanPhamEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.PhienDauGiaRepository;
import com.ute.auction.repository.SanPhamDauGiaRepository;
import com.ute.auction.repository.SanPhamRepository;
import com.ute.auction.service.ISanPhamDauGiaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SanPhamDauGiaService implements ISanPhamDauGiaService {

        private final SanPhamDauGiaConverter sanPhamDauGiaConverter;
        private final SanPhamDauGiaRepository sanPhamDauGiaRepository;
        private final PhienDauGiaRepository phienDauGiaRepository;
        private final SanPhamRepository sanPhamRepository;

        @Override
        public List<SanPhamDauGiaDTO> getSanPhamDauGias() {
                List<SanPhamDauGiaEntity> entities = sanPhamDauGiaRepository.findAll();
                return entities.stream().map(sanPhamDauGiaConverter::toDTO).collect(Collectors.toList());
        }

        @Transactional
        @Override
        public SanPhamDauGiaDTO addSanPhamDauGia(SanPhamDauGiaDTO sanPhamDauGiaDTO) {
                PhienDauGiaEntity phienDauGiaEntity = phienDauGiaRepository
                                .findOneByMaPhienDauGia(sanPhamDauGiaDTO.getMaPhienDauGia())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy phiên đấu giá nào với mã phiên đấu giá là: "
                                                                + sanPhamDauGiaDTO.getMaPhienDauGia()));
                SanPhamEntity sanPhamEntity = sanPhamRepository
                                .findOneByMaSanPham(sanPhamDauGiaDTO.getMaSanPham())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy sản phẩm nào với mã sản phẩm là: "
                                                                + sanPhamDauGiaDTO.getMaSanPham()));
                SanPhamDauGiaEntity sanPhamDauGiaEntity = sanPhamDauGiaConverter.toEntity(sanPhamDauGiaDTO);
                sanPhamDauGiaEntity.setPhienDauGia(phienDauGiaEntity);
                sanPhamDauGiaEntity.setSanPham(sanPhamEntity);
                return sanPhamDauGiaConverter.toDTO(sanPhamDauGiaRepository.save(sanPhamDauGiaEntity));
        }

        @Transactional
        @Override
        public SanPhamDauGiaDTO updateSanPhamDauGia(long maPhienDauGia, String maSanPham,
                        SanPhamDauGiaDTO updatedSanPhamDauGia) {
                SanPhamDauGiaEntity sanPhamDauGiaEntity = sanPhamDauGiaRepository
                                .findOneByMaSanPhamDauGia_MaPhienDauGiaAndMaSanPhamDauGia_MaSanPham(maPhienDauGia,
                                                maSanPham)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy sản phẩm đấu giá nào!"));

                if (updatedSanPhamDauGia.getMaPhienDauGia() != null) {
                        PhienDauGiaEntity phienDauGiaEntity = phienDauGiaRepository
                                        .findOneByMaPhienDauGia(updatedSanPhamDauGia.getMaPhienDauGia())
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Không tìm thấy phiên đấu giá nào với mã phiên đấu giá là: "
                                                                        + updatedSanPhamDauGia.getMaPhienDauGia()));
                        sanPhamDauGiaEntity.setPhienDauGia(phienDauGiaEntity);
                }
                if (updatedSanPhamDauGia.getMaSanPham() != null) {
                        SanPhamEntity sanPhamEntity = sanPhamRepository
                                        .findOneByMaSanPham(updatedSanPhamDauGia.getMaSanPham())
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Không tìm thấy sản phẩm nào với mã sản phẩm là: "
                                                                        + updatedSanPhamDauGia.getMaSanPham()));
                        sanPhamDauGiaEntity.setSanPham(sanPhamEntity);
                }
                SanPhamDauGiaEntity sanPhamDauGiaUpdated = sanPhamDauGiaConverter.toEntity(updatedSanPhamDauGia,
                                sanPhamDauGiaEntity);
                return sanPhamDauGiaConverter.toDTO(sanPhamDauGiaRepository.save(sanPhamDauGiaUpdated));
        }

}
