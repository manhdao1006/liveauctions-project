package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.SanPhamDangKyConverter;
import com.ute.auction.dto.SanPhamDangKyDTO;
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
public class SanPhamDauGiaService implements ISanPhamDangKyService {

    private final SanPhamDangKyRepository registrationProductRepository;
    private final NguoiBanRepository sellerRepository;
    private final DanhMucConRepository subCategoryRepository;
    private final LoaiDauGiaRepository auctionFormatRepository;
    private final SanPhamDangKyConverter registrationProductConverter;

    private void checkExistedSeller(long sellerId) {
        boolean sellerExists = sellerRepository.existsByMaNguoiBan(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }
    }

    /*
     * get all registration products by seller id
     * 
     * @param sellerId, page, size
     * 
     * @return registration products
     */
    @Override
    public List<SanPhamDangKyDTO> getRegistrationProductsBySellerId(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamDangKyEntity> entities = registrationProductRepository
                .findRegistrationProductsBySellerId(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(registrationProductConverter::toDTO).collect(Collectors.toList());
    }

    /*
     * register product
     * 
     * @param regisProduct
     * 
     * @return regisProduct
     */
    @Override
    @Transactional
    public SanPhamDangKyDTO registerProduct(SanPhamDangKyDTO regisProduct) {

        NguoiBanEntity seller = sellerRepository.findOneByMaNguoiBan(regisProduct.getMaNguoiBan())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy người bán nào với mã người bán là: " + regisProduct.getMaNguoiBan()));
        DanhMucConEntity subCategory = subCategoryRepository.findOneByMaDanhMucCon(regisProduct.getMaDanhMucCon())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục con nào với mã danh mục con là: " + regisProduct.getMaDanhMucCon()));
        LoaiDauGiaEntity auctionFormat = auctionFormatRepository.findOneByMaLoaiDauGia(regisProduct.getMaLoaiDauGia())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy loại đấu giá nào với mã loại đấu giá là: " + regisProduct.getMaLoaiDauGia()));

        SanPhamDangKyEntity registrationProductEntity = registrationProductConverter.toEntity(regisProduct);
        registrationProductEntity.setNguoiBan(seller);
        registrationProductEntity.setDanhMucCon(subCategory);
        registrationProductEntity.setLoaiDauGia(auctionFormat);

        registrationProductEntity = registrationProductRepository.save(registrationProductEntity);

        return registrationProductConverter.toDTO(registrationProductEntity);
    }

    @Override
    public List<SanPhamDangKyDTO> sortedAscByStartingPrice(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamDangKyEntity> entities = registrationProductRepository.sortedAscByStartingPrice(sellerId,
                pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No registration products with page: " + page);
        }

        return entities.stream().map(registrationProductConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SanPhamDangKyDTO> sortedDescByStartingPrice(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamDangKyEntity> entities = registrationProductRepository.sortedDescByStartingPrice(sellerId,
                pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No registration products with page: " + page);
        }

        return entities.stream().map(registrationProductConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SanPhamDangKyDTO> sortedAscByRegistrationDate(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamDangKyEntity> entities = registrationProductRepository.sortedAscByRegistrationDate(sellerId,
                pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No registration products with page: " + page);
        }

        return entities.stream().map(registrationProductConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SanPhamDangKyDTO> sortedDescByRegistrationDate(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamDangKyEntity> entities = registrationProductRepository.sortedDescByRegistrationDate(sellerId,
                pageable);
        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No registration products with page: " + page);
        }

        return entities.stream().map(registrationProductConverter::toDTO).collect(Collectors.toList());
    }

}
