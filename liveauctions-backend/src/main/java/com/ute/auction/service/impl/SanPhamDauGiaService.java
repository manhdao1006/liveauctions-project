package com.ute.auction.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.PhienDauGiaConverter;
import com.ute.auction.converter.SanPhamConverter;
import com.ute.auction.converter.SanPhamDauGiaConverter;
import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.dto.PhienDauGiaDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.dto.SanPhamDauGiaDTO;
import com.ute.auction.dto.SanPhamDauGiaResponseDTO;
import com.ute.auction.entity.SanPhamEntity;
import com.ute.auction.entity.PhienDauGiaEntity;
import com.ute.auction.entity.SanPhamDauGiaEntity;
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
    public PageResponse<SanPhamDauGiaResponseDTO> getSanPhamDauGias(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("ngayDangKy").descending());

        Page<SanPhamDauGiaEntity> entities = sanPhamDauGiaRepository.findAll(pageable);
        List<SanPhamDauGiaResponseDTO> responseList = new ArrayList<>();
        for (SanPhamDauGiaEntity sanPhamDauGiaEntity : entities) {
            SanPhamDauGiaDTO sanPhamDauGiaDTO = sanPhamDauGiaConverter.toDTO(sanPhamDauGiaEntity);

            PhienDauGiaEntity phienDauGiaEntity = sanPhamDauGiaEntity.getPhienDauGia();
            PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);

            SanPhamEntity sanPhamEntity = sanPhamDauGiaEntity.getSanPham();
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            responseList.add(new SanPhamDauGiaResponseDTO(sanPhamDauGiaDTO, phienDauGiaDTO, sanPhamDTO));
        }

        return PageResponse.<SanPhamDauGiaResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();

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
