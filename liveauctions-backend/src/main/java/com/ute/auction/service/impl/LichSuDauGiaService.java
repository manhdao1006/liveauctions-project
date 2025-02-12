package com.ute.auction.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.ChiPhiConverter;
import com.ute.auction.converter.LichSuDauGiaConverter;
import com.ute.auction.converter.NguoiBanConverter;
import com.ute.auction.converter.NguoiMuaConverter;
import com.ute.auction.converter.PhienDauGiaConverter;
import com.ute.auction.converter.SanPhamConverter;
import com.ute.auction.dto.ChiPhiDTO;
import com.ute.auction.dto.LichSuDauGiaDTO;
import com.ute.auction.dto.LichSuDauGiaResponseDTO;
import com.ute.auction.dto.NguoiBanDTO;
import com.ute.auction.dto.NguoiMuaDTO;
import com.ute.auction.dto.PageResponse;
import com.ute.auction.dto.PhienDauGiaDTO;
import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.entity.ChiPhiEntity;
import com.ute.auction.entity.LichSuDauGiaEntity;
import com.ute.auction.entity.NguoiBanEntity;
import com.ute.auction.entity.NguoiMuaEntity;
import com.ute.auction.entity.PhienDauGiaEntity;
import com.ute.auction.entity.SanPhamEntity;
import com.ute.auction.entity.impl.MaLichSuDauGia;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.ChiPhiRepository;
import com.ute.auction.repository.LichSuDauGiaRepository;
import com.ute.auction.repository.NguoiMuaRepository;
import com.ute.auction.repository.PhienDauGiaRepository;
import com.ute.auction.repository.SanPhamRepository;
import com.ute.auction.service.ILichSuDauGiaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LichSuDauGiaService implements ILichSuDauGiaService {

    private final LichSuDauGiaRepository lichSuDauGiaRepository;
    private final NguoiMuaRepository nguoiMuaRepository;
    private final PhienDauGiaRepository phienDauGiaRepository;
    private final SanPhamRepository sanPhamRepository;
    private final ChiPhiRepository chiPhiRepository;
    private final LichSuDauGiaConverter lichSuDauGiaConverter;
    private final PhienDauGiaConverter phienDauGiaConverter;
    private final SanPhamConverter sanPhamConverter;
    private final NguoiMuaConverter nguoiMuaConverter;
    private final NguoiBanConverter nguoiBanConverter;
    private final ChiPhiConverter chiPhiConverter;

    @Override
    public PageResponse<LichSuDauGiaResponseDTO> getLichSuDauGiasByMaNguoiMua(long maNguoiMua, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("thoiGianDauGia").descending());

        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.findByMaNguoiMua(maNguoiMua, pageable);
        List<LichSuDauGiaResponseDTO> responseList = new ArrayList<>();
        for (LichSuDauGiaEntity lichSuDauGiaEntity : entities) {
            LichSuDauGiaDTO lichSuDauGiaDTO = lichSuDauGiaConverter.toDTO(lichSuDauGiaEntity);

            PhienDauGiaEntity phienDauGiaEntity = lichSuDauGiaEntity.getPhienDauGia();
            PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);

            SanPhamEntity sanPhamEntity = lichSuDauGiaEntity.getSanPham();
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            NguoiMuaEntity nguoiMuaEntity = lichSuDauGiaEntity.getNguoiMua();
            NguoiMuaDTO nguoiMuaDTO = nguoiMuaConverter.toDTO(nguoiMuaEntity);

            NguoiBanEntity nguoiBanEntity = lichSuDauGiaEntity.getSanPham().getNguoiBan();
            NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);

            ChiPhiEntity chiPhiEntity = lichSuDauGiaEntity.getChiPhi();
            ChiPhiDTO chiPhiDTO = chiPhiConverter.toDTO(chiPhiEntity);

            responseList.add(new LichSuDauGiaResponseDTO(lichSuDauGiaDTO, phienDauGiaDTO, sanPhamDTO,
                    nguoiMuaDTO, nguoiBanDTO, chiPhiDTO));
        }

        return PageResponse.<LichSuDauGiaResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();
    }

    @Override
    public PageResponse<LichSuDauGiaResponseDTO> getLichSuDauGiasByMaNguoiBan(long maNguoiBan, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("thoiGianDauGia").descending());

        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.findByMaNguoiBan(maNguoiBan, pageable);
        List<LichSuDauGiaResponseDTO> responseList = new ArrayList<>();
        for (LichSuDauGiaEntity lichSuDauGiaEntity : entities) {
            LichSuDauGiaDTO lichSuDauGiaDTO = lichSuDauGiaConverter.toDTO(lichSuDauGiaEntity);

            PhienDauGiaEntity phienDauGiaEntity = lichSuDauGiaEntity.getPhienDauGia();
            PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);

            SanPhamEntity sanPhamEntity = lichSuDauGiaEntity.getSanPham();
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            NguoiMuaEntity nguoiMuaEntity = lichSuDauGiaEntity.getNguoiMua();
            NguoiMuaDTO nguoiMuaDTO = nguoiMuaConverter.toDTO(nguoiMuaEntity);

            NguoiBanEntity nguoiBanEntity = lichSuDauGiaEntity.getSanPham().getNguoiBan();
            NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);

            ChiPhiEntity chiPhiEntity = lichSuDauGiaEntity.getChiPhi();
            ChiPhiDTO chiPhiDTO = chiPhiConverter.toDTO(chiPhiEntity);

            responseList.add(new LichSuDauGiaResponseDTO(lichSuDauGiaDTO, phienDauGiaDTO, sanPhamDTO,
                    nguoiMuaDTO, nguoiBanDTO, chiPhiDTO));
        }

        return PageResponse.<LichSuDauGiaResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();
    }

    @Override
    public PageResponse<LichSuDauGiaResponseDTO> getByTrangThaiDonHangNguoiMua(long maNguoiMua,
            String trangThaiDonHang, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("thoiGianDauGia").descending());

        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.findByTrangThaiDonHangNguoiMua(maNguoiMua,
                trangThaiDonHang, pageable);
        List<LichSuDauGiaResponseDTO> responseList = new ArrayList<>();
        for (LichSuDauGiaEntity lichSuDauGiaEntity : entities) {
            LichSuDauGiaDTO lichSuDauGiaDTO = lichSuDauGiaConverter.toDTO(lichSuDauGiaEntity);

            PhienDauGiaEntity phienDauGiaEntity = lichSuDauGiaEntity.getPhienDauGia();
            PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);

            SanPhamEntity sanPhamEntity = lichSuDauGiaEntity.getSanPham();
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            NguoiMuaEntity nguoiMuaEntity = lichSuDauGiaEntity.getNguoiMua();
            NguoiMuaDTO nguoiMuaDTO = nguoiMuaConverter.toDTO(nguoiMuaEntity);

            NguoiBanEntity nguoiBanEntity = lichSuDauGiaEntity.getSanPham().getNguoiBan();
            NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);

            ChiPhiEntity chiPhiEntity = lichSuDauGiaEntity.getChiPhi();
            ChiPhiDTO chiPhiDTO = chiPhiConverter.toDTO(chiPhiEntity);

            responseList.add(new LichSuDauGiaResponseDTO(lichSuDauGiaDTO, phienDauGiaDTO, sanPhamDTO,
                    nguoiMuaDTO, nguoiBanDTO, chiPhiDTO));
        }

        return PageResponse.<LichSuDauGiaResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();
    }

    @Override
    public PageResponse<LichSuDauGiaResponseDTO> getByTrangThaiDonHangNguoiBan(long maNguoiBan,
            String trangThaiDonHang, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("thoiGianDauGia").descending());

        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.findByTrangThaiDonHangNguoiBan(maNguoiBan,
                trangThaiDonHang, pageable);
        List<LichSuDauGiaResponseDTO> responseList = new ArrayList<>();
        for (LichSuDauGiaEntity lichSuDauGiaEntity : entities) {
            LichSuDauGiaDTO lichSuDauGiaDTO = lichSuDauGiaConverter.toDTO(lichSuDauGiaEntity);

            PhienDauGiaEntity phienDauGiaEntity = lichSuDauGiaEntity.getPhienDauGia();
            PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);

            SanPhamEntity sanPhamEntity = lichSuDauGiaEntity.getSanPham();
            SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);

            NguoiMuaEntity nguoiMuaEntity = lichSuDauGiaEntity.getNguoiMua();
            NguoiMuaDTO nguoiMuaDTO = nguoiMuaConverter.toDTO(nguoiMuaEntity);

            NguoiBanEntity nguoiBanEntity = lichSuDauGiaEntity.getSanPham().getNguoiBan();
            NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(nguoiBanEntity);

            ChiPhiEntity chiPhiEntity = lichSuDauGiaEntity.getChiPhi();
            ChiPhiDTO chiPhiDTO = chiPhiConverter.toDTO(chiPhiEntity);

            responseList.add(new LichSuDauGiaResponseDTO(lichSuDauGiaDTO, phienDauGiaDTO, sanPhamDTO,
                    nguoiMuaDTO, nguoiBanDTO, chiPhiDTO));
        }

        return PageResponse.<LichSuDauGiaResponseDTO>builder()
                .currentPage(page)
                .pageSize(entities.getSize())
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .data(responseList)
                .build();
    }

    @Transactional
    @Override
    public LichSuDauGiaResponseDTO addLichSuDauGia(LichSuDauGiaDTO lichSuDauGiaDTO) {
        PhienDauGiaEntity phienDauGiaEntity = phienDauGiaRepository
                .findOneByMaPhienDauGia(lichSuDauGiaDTO.getMaPhienDauGia())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy phiên đấu giá nào với mã phiên đấu giá là: "
                                + lichSuDauGiaDTO.getMaPhienDauGia()));
        SanPhamEntity sanPhamEntity = sanPhamRepository.findOneByMaSanPham(lichSuDauGiaDTO.getMaSanPham())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy sản phẩm nào với mã sản phẩm là: "
                                + lichSuDauGiaDTO.getMaSanPham()));
        NguoiMuaEntity nguoiMuaEntity = nguoiMuaRepository.findOneByMaNguoiMua(lichSuDauGiaDTO.getMaNguoiMua())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người mua nào với mã người mua là: "
                                + lichSuDauGiaDTO.getMaNguoiMua()));
        ChiPhiEntity chiPhiEntity = chiPhiRepository.findOneByMaChiPhi(lichSuDauGiaDTO.getMaChiPhi())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy chi phí nào với mã chi phí là: "
                                + lichSuDauGiaDTO.getMaChiPhi()));

        LichSuDauGiaEntity lichSuDauGiaEntity = lichSuDauGiaConverter.toEntity(lichSuDauGiaDTO);
        MaLichSuDauGia maLichSuDauGia = new MaLichSuDauGia(
                lichSuDauGiaDTO.getMaPhienDauGia(),
                lichSuDauGiaDTO.getMaSanPham(),
                lichSuDauGiaDTO.getMaNguoiMua(),
                lichSuDauGiaDTO.getMaChiPhi());
        lichSuDauGiaEntity.setMaLichSuDauGia(maLichSuDauGia);
        lichSuDauGiaEntity.setThoiGianDauGia(LocalDateTime.now());
        lichSuDauGiaEntity.setPhienDauGia(phienDauGiaEntity);
        lichSuDauGiaEntity.setSanPham(sanPhamEntity);
        lichSuDauGiaEntity.setNguoiMua(nguoiMuaEntity);
        lichSuDauGiaEntity.setChiPhi(chiPhiEntity);

        lichSuDauGiaEntity = lichSuDauGiaRepository.save(lichSuDauGiaEntity);

        LichSuDauGiaDTO lichSuDauGiaDTOResponse = lichSuDauGiaConverter.toDTO(lichSuDauGiaEntity);
        PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(phienDauGiaEntity);
        SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(sanPhamEntity);
        NguoiMuaDTO nguoiMuaDTO = nguoiMuaConverter.toDTO(nguoiMuaEntity);
        NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(sanPhamEntity.getNguoiBan());
        ChiPhiDTO chiPhiDTO = chiPhiConverter.toDTO(chiPhiEntity);

        return new LichSuDauGiaResponseDTO(lichSuDauGiaDTOResponse, phienDauGiaDTO, sanPhamDTO, nguoiMuaDTO,
                nguoiBanDTO, chiPhiDTO);
    }

    @Transactional
    @Override
    public LichSuDauGiaResponseDTO updateLichSuDauGia(long maPhienDauGia, String maSanPham, long maNguoiMua,
            long maChiPhi, LichSuDauGiaDTO lichSuDauGiaDTO) {
        LichSuDauGiaEntity lichSuDauGiaEntity = lichSuDauGiaRepository
                .findOneByMaLichSuDauGia_MaPhienDauGiaAndMaLichSuDauGia_MaSanPhamAndMaLichSuDauGia_MaNguoiMuaAndMaLichSuDauGia_MaChiPhi(
                        maPhienDauGia, maSanPham, maNguoiMua, maChiPhi)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy lịch sử đấu giá nào!"));

        if (lichSuDauGiaDTO.getMaPhienDauGia() != null) {
            PhienDauGiaEntity phienDauGiaEntity = phienDauGiaRepository
                    .findOneByMaPhienDauGia(lichSuDauGiaDTO.getMaPhienDauGia())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy phiên đấu giá nào với mã phiên đấu giá là: "
                                    + lichSuDauGiaDTO.getMaPhienDauGia()));
            lichSuDauGiaEntity.setPhienDauGia(phienDauGiaEntity);
        }
        if (lichSuDauGiaDTO.getMaSanPham() != null) {
            SanPhamEntity sanPhamEntity = sanPhamRepository
                    .findOneByMaSanPham(lichSuDauGiaDTO.getMaSanPham())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy sản phẩm nào với mã sản phẩm là: "
                                    + lichSuDauGiaDTO.getMaSanPham()));
            lichSuDauGiaEntity.setSanPham(sanPhamEntity);
        }
        if (lichSuDauGiaDTO.getMaNguoiMua() != null) {
            NguoiMuaEntity nguoiMuaEntity = nguoiMuaRepository
                    .findOneByMaNguoiMua(lichSuDauGiaDTO.getMaNguoiMua())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy người mua nào với mã người mua là: "
                                    + lichSuDauGiaDTO.getMaNguoiMua()));
            lichSuDauGiaEntity.setNguoiMua(nguoiMuaEntity);
        }
        if (lichSuDauGiaDTO.getMaChiPhi() != null) {
            ChiPhiEntity chiPhiEntity = chiPhiRepository.findOneByMaChiPhi(lichSuDauGiaDTO.getMaChiPhi())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Không tìm thấy chi phí nào với mã chi phí là: "
                                    + lichSuDauGiaDTO.getMaChiPhi()));
            lichSuDauGiaEntity.setChiPhi(chiPhiEntity);
        }
        if (lichSuDauGiaDTO.getGiaDaDauGia() != null
                && !lichSuDauGiaDTO.getGiaDaDauGia().equals(lichSuDauGiaEntity.getGiaDaDauGia())) {
            lichSuDauGiaEntity.setThoiGianDauGia(LocalDateTime.now());
        }

        LichSuDauGiaEntity lichSuDauGiaUpdated = lichSuDauGiaConverter.toEntity(lichSuDauGiaDTO,
                lichSuDauGiaEntity);
        lichSuDauGiaUpdated = lichSuDauGiaRepository.save(lichSuDauGiaUpdated);

        LichSuDauGiaDTO lichSuDauGiaResponseDTO = lichSuDauGiaConverter.toDTO(lichSuDauGiaUpdated);
        PhienDauGiaDTO phienDauGiaDTO = phienDauGiaConverter.toDTO(lichSuDauGiaUpdated.getPhienDauGia());
        SanPhamDTO sanPhamDTO = sanPhamConverter.toDTO(lichSuDauGiaUpdated.getSanPham());
        NguoiMuaDTO nguoiMuaDTO = nguoiMuaConverter.toDTO(lichSuDauGiaUpdated.getNguoiMua());
        NguoiBanDTO nguoiBanDTO = nguoiBanConverter.toDTO(lichSuDauGiaUpdated.getSanPham().getNguoiBan());
        ChiPhiDTO chiPhiDTO = chiPhiConverter.toDTO(lichSuDauGiaUpdated.getChiPhi());

        return new LichSuDauGiaResponseDTO(lichSuDauGiaResponseDTO, phienDauGiaDTO,
                sanPhamDTO, nguoiMuaDTO,
                nguoiBanDTO, chiPhiDTO);
    }

}
