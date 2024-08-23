package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.ProductConverter;
import com.ute.auction.dto.ProductDTO;
import com.ute.auction.entity.ProductEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.ProductRepository;
import com.ute.auction.repository.SellerRepository;
import com.ute.auction.service.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final ProductConverter productConverter;

    /*
     * get all products by seller id
     * @param sellerId, page, size
     * @return products
     */
    @Override
    public List<ProductDTO> getProductsBySellerId(Long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProductEntity> entities = productRepository.findProductsBySellerId(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(productConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortedAscByStartingPrice(Long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProductEntity> entities = productRepository.sortedAscByStartingPrice(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(productConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortedDescByStartingPrice(Long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProductEntity> entities = productRepository.sortedAscByStartingPrice(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No products with page: " + page);
        }

        return entities.stream().map(productConverter::toDTO).collect(Collectors.toList());
    }

    private void checkExistedSeller(Long sellerId) {
        boolean sellerExists = sellerRepository.existsBySellerId(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }
    }

}
