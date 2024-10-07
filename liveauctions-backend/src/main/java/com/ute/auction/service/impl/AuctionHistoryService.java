package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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

@Service
@RequiredArgsConstructor
public class AuctionHistoryService implements IAuctionHistoryService {

    private final AuctionHistoryRepository auctionHistoryRepository;
    private final SellerRepository sellerRepository;
    private final AuctionHistoryConverter auctionHistoryConverter;

    /*
     * get all orders by seller id
     * 
     * @param sellerId, page, size
     * 
     * @return orders
     */
    @Override
    public List<AuctionHistoryDTO> getOrders(int sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuctionHistoryEntity> entities = auctionHistoryRepository.findOrdersBySellerId(sellerId, pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No orders with page: " + page);
        }

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    /*
     * get all orders by order status
     * 
     * @param sellerId, orderStatus, page, size
     * 
     * @return orders
     */
    @Override
    public List<AuctionHistoryDTO> getOrdersByOrderStatus(int sellerId, String orderStatus, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuctionHistoryEntity> entities = auctionHistoryRepository.findOrdersByOrderStatus(sellerId, orderStatus,
                pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No orders with page: " + page);
        }

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    private void checkExistedSeller(int sellerId) {
        boolean sellerExists = sellerRepository.existsBySellerId(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }
    }

    @Override
    public List<AuctionHistoryDTO> sortedAscByAuctionedPrice(int sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuctionHistoryEntity> entities = auctionHistoryRepository.sortedAscByAuctionedPrice(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<AuctionHistoryDTO> sortedDescByAuctionedPrice(int sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuctionHistoryEntity> entities = auctionHistoryRepository.sortedDescByAuctionedPrice(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<AuctionHistoryDTO> sortedAscByOrderDate(int sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuctionHistoryEntity> entities = auctionHistoryRepository.sortedAscByOrderDate(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<AuctionHistoryDTO> sortedDescByOrderDate(int sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuctionHistoryEntity> entities = auctionHistoryRepository.sortedDescByOrderDate(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<AuctionHistoryDTO> sortedAscByDeliveryDate(int sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuctionHistoryEntity> entities = auctionHistoryRepository.sortedAscByDeliveryDate(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<AuctionHistoryDTO> sortedDescByDeliveryDate(int sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuctionHistoryEntity> entities = auctionHistoryRepository.sortedDescByDeliveryDate(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

}
