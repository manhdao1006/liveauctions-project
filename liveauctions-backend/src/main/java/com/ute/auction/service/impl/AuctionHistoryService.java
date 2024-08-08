package com.ute.auction.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.AuctionHistoryConverter;
import com.ute.auction.dto.AuctionHistoryDTO;
import com.ute.auction.entity.AuctionHistoryEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.AuctionHistoryRepository;
import com.ute.auction.repository.SellerRepository;
import com.ute.auction.service.IAuctionHistoryService;

import lombok.RequiredArgsConstructor;

/**
 * AuctionHistoryService
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
public class AuctionHistoryService implements IAuctionHistoryService {

    private final AuctionHistoryRepository auctionHistoryRepository;
    private final SellerRepository sellerRepository;
    private final AuctionHistoryConverter auctionHistoryConverter;

    /*
     * get all orders by seller id
     * @param sellerId, page, size
     * @return orders
     */
    @Override
    public List<AuctionHistoryDTO> getOrders(Long sellerId, int page, int size) {
        boolean sellerExists = sellerRepository.existsBySellerId(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuctionHistoryEntity> entities = auctionHistoryRepository.findOrdersBySellerId(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No orders with page: " + page);
        }
        List<AuctionHistoryDTO> models = new ArrayList<>();
        for (AuctionHistoryEntity item: entities) {
            AuctionHistoryDTO auctionHistoryDTO = auctionHistoryConverter.toDTO(item);
            models.add(auctionHistoryDTO);
        }

        return models;
    }

    /*
     * get all orders by order status
     * @param sellerId, orderStatus, page, size
     * @return orders
     */
    @Override
    public List<AuctionHistoryDTO> getOrdersByOrderStatus(Long sellerId, String orderStatus, int page, int size) {
        boolean sellerExists = sellerRepository.existsBySellerId(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuctionHistoryEntity> entities = auctionHistoryRepository.findOrdersByOrderStatus(sellerId, orderStatus, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No orders with page: " + page);
        }
        List<AuctionHistoryDTO> models = new ArrayList<>();
        for (AuctionHistoryEntity item: entities) {
            AuctionHistoryDTO auctionHistoryDTO = auctionHistoryConverter.toDTO(item);
            models.add(auctionHistoryDTO);
        }

        return models;
    }

}
