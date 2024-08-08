package com.ute.auction.service.impl;

import java.util.ArrayList;
import java.util.List;

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

/**
 * ProductService
 *
 * Version 1.0
 *
 * Date: 17-07-2024
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * --------------------------------------------------------
 * 17-07-2024           ManhDao         Create
 */
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
        boolean sellerExists = sellerRepository.existsBySellerId(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProductEntity> entities = productRepository.findProductsBySellerId(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No orders with page: " + page);
        }
        List<ProductDTO> models = new ArrayList<>();
        for (ProductEntity item: entities) {
            ProductDTO productDTO = productConverter.toDTO(item);
            models.add(productDTO);
        }

        return models;
    }
}