package com.ute.auction.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.PhienDauGiaConverter;
import com.ute.auction.converter.SanPhamConverter;
import com.ute.auction.converter.SanPhamDauGiaConverter;
import com.ute.auction.dto.PhienDauGiaDTO;
import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.dto.SanPhamDauGiaDTO;
import com.ute.auction.dto.SanPhamDauGiaResponseDTO;
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
    private final PhienDauGiaConverter phienDauGiaConverter;
    private final SanPhamConverter sanPhamConverter;

    @Override
    public List<SanPhamDauGiaResponseDTO> getSanPhamDauGias() {
        List<SanPhamDauGiaEntity> entities = sanPhamDauGiaRepository.findAll();
        List<SanPhamDauGiaResponseDTO> responseList = new ArrayList<>();
        for (SanPhamDauGiaEntity sanPhamDauGiaEntity : entities) {
            SanPhamDauGiaDTO sanPhamDauGiaDTO = sanPhamDauGiaConverter.toDTO(sanPhamDauGiaEntity);

            PhienDauGiaEntity phienDauGiaEntity = sanPhamDauGiaEntity.getPhienDauGia();
            PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);

            SanPhamEntity sanPhamEntity = sanPhamDauGiaEntity.getSanPham();
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            responseList.add(new SanPhamDauGiaResponseDTO(sanPhamDauGiaDTO, phienDauGiaDTO, sanPhamDTO));
        }

        return responseList;

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

    @Override
    public List<SanPhamDauGiaResponseDTO> getSanPhamDauGiasUpcoming() {
        List<SanPhamDauGiaEntity> entities = sanPhamDauGiaRepository
                .findFirst2ByPhienDauGia_TrangThaiHoatDongOrderByPhienDauGia_NgayBatDauAsc("Sắp diễn ra");
        List<SanPhamDauGiaResponseDTO> responseList = new ArrayList<>();
        for (SanPhamDauGiaEntity sanPhamDauGiaEntity : entities) {
            SanPhamDauGiaDTO sanPhamDauGiaDTO = sanPhamDauGiaConverter.toDTO(sanPhamDauGiaEntity);

            PhienDauGiaEntity phienDauGiaEntity = sanPhamDauGiaEntity.getPhienDauGia();
            PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);

            SanPhamEntity sanPhamEntity = sanPhamDauGiaEntity.getSanPham();
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            responseList.add(new SanPhamDauGiaResponseDTO(sanPhamDauGiaDTO, phienDauGiaDTO, sanPhamDTO));
        }

        return responseList;
    }

    @Override
    public List<SanPhamDauGiaResponseDTO> getSanPhamDauGiasTrending() {
        List<SanPhamDauGiaEntity> entities = sanPhamDauGiaRepository
                .findFirst8ByPhienDauGia_TrangThaiHoatDongOrderBySanPham_GiaKhoiDiemDesc("Đang diễn ra");
        List<SanPhamDauGiaResponseDTO> responseList = new ArrayList<>();
        for (SanPhamDauGiaEntity sanPhamDauGiaEntity : entities) {
            SanPhamDauGiaDTO sanPhamDauGiaDTO = sanPhamDauGiaConverter.toDTO(sanPhamDauGiaEntity);

            PhienDauGiaEntity phienDauGiaEntity = sanPhamDauGiaEntity.getPhienDauGia();
            PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);

            SanPhamEntity sanPhamEntity = sanPhamDauGiaEntity.getSanPham();
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            responseList.add(new SanPhamDauGiaResponseDTO(sanPhamDauGiaDTO, phienDauGiaDTO, sanPhamDTO));
        }

        return responseList;
    }

    @Override
    public List<SanPhamDauGiaResponseDTO> getSanPhamDauGiasKin() {
        List<SanPhamDauGiaEntity> entities = sanPhamDauGiaRepository
                .findFirst8ByPhienDauGia_TrangThaiHoatDongAndPhienDauGia_LoaiDauGia_TenLoaiDauGiaOrderByPhienDauGia_NgayBatDauAsc(
                        "Đang diễn ra", "Đấu giá kín");
        List<SanPhamDauGiaResponseDTO> responseList = new ArrayList<>();
        for (SanPhamDauGiaEntity sanPhamDauGiaEntity : entities) {
            SanPhamDauGiaDTO sanPhamDauGiaDTO = sanPhamDauGiaConverter.toDTO(sanPhamDauGiaEntity);

            PhienDauGiaEntity phienDauGiaEntity = sanPhamDauGiaEntity.getPhienDauGia();
            PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);

            SanPhamEntity sanPhamEntity = sanPhamDauGiaEntity.getSanPham();
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            responseList.add(new SanPhamDauGiaResponseDTO(sanPhamDauGiaDTO, phienDauGiaDTO, sanPhamDTO));
        }

        return responseList;
    }

    @Override
    public List<SanPhamDauGiaResponseDTO> getSanPhamDauGiasOnline() {
        List<SanPhamDauGiaEntity> entities = sanPhamDauGiaRepository
                .findFirst6ByPhienDauGia_TrangThaiHoatDongAndPhienDauGia_LoaiDauGia_TenLoaiDauGiaOrderByPhienDauGia_NgayBatDauAsc(
                        "Đang diễn ra", "Đấu giá trực tuyến");
        List<SanPhamDauGiaResponseDTO> responseList = new ArrayList<>();
        for (SanPhamDauGiaEntity sanPhamDauGiaEntity : entities) {
            SanPhamDauGiaDTO sanPhamDauGiaDTO = sanPhamDauGiaConverter.toDTO(sanPhamDauGiaEntity);

            PhienDauGiaEntity phienDauGiaEntity = sanPhamDauGiaEntity.getPhienDauGia();
            PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);

            SanPhamEntity sanPhamEntity = sanPhamDauGiaEntity.getSanPham();
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            responseList.add(new SanPhamDauGiaResponseDTO(sanPhamDauGiaDTO, phienDauGiaDTO, sanPhamDTO));
        }

        return responseList;
    }

}
