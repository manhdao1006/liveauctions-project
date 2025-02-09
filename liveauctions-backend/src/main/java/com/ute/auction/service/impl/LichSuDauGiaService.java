package com.ute.auction.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.LichSuDauGiaRepository;
import com.ute.auction.repository.NguoiBanRepository;
import com.ute.auction.service.ILichSuDauGiaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LichSuDauGiaService implements ILichSuDauGiaService {

    private final LichSuDauGiaRepository lichSuDauGiaRepository;
    private final NguoiBanRepository nguoiBanRepository;
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

            responseList.add(
                    new LichSuDauGiaResponseDTO(lichSuDauGiaDTO, phienDauGiaDTO, sanPhamDTO, nguoiMuaDTO, nguoiBanDTO,
                            chiPhiDTO));

        }

        return PageResponse.<LichSuDauGiaResponseDTO>builder().currentPage(page).pageSize(entities.getSize())
                .totalPages(entities.getTotalPages()).totalElements(entities.getTotalElements()).data(responseList)
                .build();
    }

    /*
     * get all orders by seller id
     * 
     * @param sellerId, page, size
     * 
     * @return orders
     */
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

            responseList.add(
                    new LichSuDauGiaResponseDTO(lichSuDauGiaDTO, phienDauGiaDTO, sanPhamDTO, nguoiMuaDTO, nguoiBanDTO,
                            chiPhiDTO));

        }

        return PageResponse.<LichSuDauGiaResponseDTO>builder().currentPage(page).pageSize(entities.getSize())
                .totalPages(entities.getTotalPages()).totalElements(entities.getTotalElements()).data(responseList)
                .build();
    }

    /*
     * get all orders by order status
     * 
     * @param sellerId, orderStatus, page, size
     * 
     * @return orders
     */
    @Override
    public List<LichSuDauGiaDTO> getOrdersByOrderStatus(long sellerId, String orderStatus, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.findOrdersByOrderStatus(sellerId, orderStatus,
                pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No orders with page: " + page);
        }

        return entities.stream().map(lichSuDauGiaConverter::toDTO).collect(Collectors.toList());
    }

    private void checkExistedSeller(long sellerId) {
        boolean sellerExists = nguoiBanRepository.existsByMaNguoiBan(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }
    }

    @Override
    public List<LichSuDauGiaDTO> sortedAscByAuctionedPrice(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.sortedAscByAuctionedPrice(sellerId, pageable);

        return entities.stream().map(lichSuDauGiaConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LichSuDauGiaDTO> sortedDescByAuctionedPrice(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.sortedDescByAuctionedPrice(sellerId, pageable);

        return entities.stream().map(lichSuDauGiaConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LichSuDauGiaDTO> sortedAscByOrderDate(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.sortedAscByOrderDate(sellerId, pageable);

        return entities.stream().map(lichSuDauGiaConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LichSuDauGiaDTO> sortedDescByOrderDate(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.sortedDescByOrderDate(sellerId, pageable);

        return entities.stream().map(lichSuDauGiaConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LichSuDauGiaDTO> sortedAscByDeliveryDate(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.sortedAscByDeliveryDate(sellerId, pageable);

        return entities.stream().map(lichSuDauGiaConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LichSuDauGiaDTO> sortedDescByDeliveryDate(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = lichSuDauGiaRepository.sortedDescByDeliveryDate(sellerId, pageable);

        return entities.stream().map(lichSuDauGiaConverter::toDTO).collect(Collectors.toList());
    }

}
