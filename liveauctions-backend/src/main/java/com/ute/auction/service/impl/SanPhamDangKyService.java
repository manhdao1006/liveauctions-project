package com.ute.auction.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.DanhMucConConverter;
import com.ute.auction.converter.LoaiDauGiaConverter;
import com.ute.auction.converter.NguoiBanConverter;
import com.ute.auction.converter.SanPhamDangKyConverter;
import com.ute.auction.dto.DanhMucConDTO;
import com.ute.auction.dto.LoaiDauGiaDTO;
import com.ute.auction.dto.NguoiBanDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.dto.SanPhamDangKyDTO;
import com.ute.auction.dto.SanPhamDangKyResponseDTO;
import com.ute.auction.entity.DanhMucConEntity;
import com.ute.auction.entity.LoaiDauGiaEntity;
import com.ute.auction.entity.NguoiBanEntity;
import com.ute.auction.entity.SanPhamDangKyEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.DanhMucConRepository;
import com.ute.auction.repository.LoaiDauGiaRepository;
import com.ute.auction.repository.NguoiBanRepository;
import com.ute.auction.repository.SanPhamDangKyRepository;
import com.ute.auction.service.ISanPhamDangKyService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SanPhamDangKyService implements ISanPhamDangKyService {

    private final SanPhamDangKyRepository sanPhamDangKyRepository;
    private final NguoiBanRepository nguoiBanRepository;
    private final DanhMucConRepository danhMucConRepository;
    private final LoaiDauGiaRepository loaiDauGiaRepository;
    private final SanPhamDangKyConverter sanPhamDangKyConverter;
    private final NguoiBanConverter nguoiBanConverter;
    private final DanhMucConConverter danhMucConConverter;
    private final LoaiDauGiaConverter loaiDauGiaConverter;

    @Override
    public PageResponse<SanPhamDangKyResponseDTO> getSanPhamDangKys(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("ngayDangKy").descending());

        Page<SanPhamDangKyEntity> entities = sanPhamDangKyRepository.findSanPhamDangKysByTrangThaiXoa("1",
                pageable);
        List<SanPhamDangKyResponseDTO> responseList = new ArrayList<>();
        for (SanPhamDangKyEntity sanPhamDangKyEntity : entities) {
            SanPhamDangKyDTO sanPhamDangKyDTO = sanPhamDangKyConverter.toDTO(sanPhamDangKyEntity);

            NguoiBanEntity nguoiBanEntity = sanPhamDangKyEntity.getNguoiBan();
            NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);

            DanhMucConEntity danhMucConEntity = sanPhamDangKyEntity.getDanhMucCon();
            DanhMucConDTO danhMucConDTO = danhMucConConverter.toDTO(danhMucConEntity);

            LoaiDauGiaEntity loaiDauGiaEntity = sanPhamDangKyEntity.getLoaiDauGia();
            LoaiDauGiaDTO loaiDauGiaDTO = loaiDauGiaConverter.toDTO(loaiDauGiaEntity);

            responseList.add(new SanPhamDangKyResponseDTO(sanPhamDangKyDTO, nguoiBanDTO, danhMucConDTO,
                    loaiDauGiaDTO));
        }

        return PageResponse.<SanPhamDangKyResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();
    }

    @Override
    public SanPhamDangKyResponseDTO getSanPhamDangKyByMaSanPhamDangKy(long maSanPhamDangKy) {
        SanPhamDangKyEntity sanPhamDangKyEntity = sanPhamDangKyRepository
                .findOneByMaSanPhamDangKyAndTrangThaiXoa(maSanPhamDangKy, "1")
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy sản phẩm đăng ký nào với mã sản phẩm đăng ký là: "
                                + maSanPhamDangKy));
        SanPhamDangKyDTO sanPhamDangKyDTO = sanPhamDangKyConverter.toDTO(sanPhamDangKyEntity);

        NguoiBanEntity nguoiBanEntity = sanPhamDangKyEntity.getNguoiBan();
        NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);

        DanhMucConEntity danhMucConEntity = sanPhamDangKyEntity.getDanhMucCon();
        DanhMucConDTO danhMucConDTO = danhMucConConverter.toDTO(danhMucConEntity);

        LoaiDauGiaEntity loaiDauGiaEntity = sanPhamDangKyEntity.getLoaiDauGia();
        LoaiDauGiaDTO loaiDauGiaDTO = loaiDauGiaConverter.toDTO(loaiDauGiaEntity);

        return new SanPhamDangKyResponseDTO(sanPhamDangKyDTO, nguoiBanDTO, danhMucConDTO, loaiDauGiaDTO);
    }

    @Override
    public PageResponse<SanPhamDangKyResponseDTO> getSanPhamDangKysByMaNguoiBan(long maNguoiBan, int page,
            int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("ngayDangKy").descending());

        Page<SanPhamDangKyEntity> entities = sanPhamDangKyRepository
                .findSanPhamDangKysByNguoiBan_MaNguoiBanAndTrangThaiXoa(maNguoiBan, "1", pageable);
        List<SanPhamDangKyResponseDTO> responseList = new ArrayList<>();
        for (SanPhamDangKyEntity sanPhamDangKyEntity : entities) {
            SanPhamDangKyDTO sanPhamDangKyDTO = sanPhamDangKyConverter.toDTO(sanPhamDangKyEntity);

            NguoiBanEntity nguoiBanEntity = sanPhamDangKyEntity.getNguoiBan();
            NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);

            DanhMucConEntity danhMucConEntity = sanPhamDangKyEntity.getDanhMucCon();
            DanhMucConDTO danhMucConDTO = danhMucConConverter.toDTO(danhMucConEntity);

            LoaiDauGiaEntity loaiDauGiaEntity = sanPhamDangKyEntity.getLoaiDauGia();
            LoaiDauGiaDTO loaiDauGiaDTO = loaiDauGiaConverter.toDTO(loaiDauGiaEntity);

            responseList.add(new SanPhamDangKyResponseDTO(sanPhamDangKyDTO, nguoiBanDTO, danhMucConDTO,
                    loaiDauGiaDTO));
        }

        return PageResponse.<SanPhamDangKyResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();
    }

    @Transactional
    @Override
    public SanPhamDangKyDTO registerSanPham(SanPhamDangKyDTO sanPhamDangKyDTO) {

        NguoiBanEntity seller = nguoiBanRepository.findOneByMaNguoiBan(sanPhamDangKyDTO.getMaNguoiBan())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người bán nào với mã người bán là: "
                                + sanPhamDangKyDTO.getMaNguoiBan()));
        DanhMucConEntity subCategory = danhMucConRepository
                .findOneByMaDanhMucCon(sanPhamDangKyDTO.getMaDanhMucCon())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục con nào với mã danh mục con là: "
                                + sanPhamDangKyDTO.getMaDanhMucCon()));
        LoaiDauGiaEntity auctionFormat = loaiDauGiaRepository
                .findOneByMaLoaiDauGia(sanPhamDangKyDTO.getMaLoaiDauGia())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy loại đấu giá nào với mã loại đấu giá là: "
                                + sanPhamDangKyDTO.getMaLoaiDauGia()));

        SanPhamDangKyEntity registrationProductEntity = sanPhamDangKyConverter.toEntity(sanPhamDangKyDTO);
        registrationProductEntity.setNguoiBan(seller);
        registrationProductEntity.setDanhMucCon(subCategory);
        registrationProductEntity.setLoaiDauGia(auctionFormat);

        registrationProductEntity = sanPhamDangKyRepository.save(registrationProductEntity);

        return sanPhamDangKyConverter.toDTO(registrationProductEntity);
    }

}
