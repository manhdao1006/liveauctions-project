package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.SanPhamConverter;
import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.entity.SanPhamEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NguoiBanRepository;
import com.ute.auction.repository.SanPhamRepository;
import com.ute.auction.service.ISanPhamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SanPhamService implements ISanPhamService {

    private final SanPhamRepository productRepository;
    private final NguoiBanRepository sellerRepository;
    private final SanPhamConverter productConverter;

    /*
     * get all products by seller id
     * 
     * @param sellerId, page, size
     * 
     * @return products
     */
    @Override
    public List<SanPhamDTO> getProductsBySellerId(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamEntity> entities = productRepository.findProductsBySellerId(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(productConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SanPhamDTO> sortedAscByStartingPrice(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamEntity> entities = productRepository.sortedAscByStartingPrice(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(productConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SanPhamDTO> sortedDescByStartingPrice(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SanPhamEntity> entities = productRepository.sortedAscByStartingPrice(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(productConverter::toDTO).collect(Collectors.toList());
    }

    private void checkExistedSeller(long sellerId) {
        boolean sellerExists = sellerRepository.existsByMaNguoiBan(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }
    }

}
