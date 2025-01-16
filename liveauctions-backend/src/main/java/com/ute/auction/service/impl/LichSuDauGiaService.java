package com.ute.auction.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ute.auction.converter.LichSuDauGiaConverter;
import com.ute.auction.dto.LichSuDauGiaDTO;
import com.ute.auction.entity.LichSuDauGiaEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.LichSuDauGiaRepository;
import com.ute.auction.repository.NguoiBanRepository;
import com.ute.auction.service.ILichSuDauGiaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LichSuDauGiaService implements ILichSuDauGiaService {

    private final LichSuDauGiaRepository auctionHistoryRepository;
    private final NguoiBanRepository sellerRepository;
    private final LichSuDauGiaConverter auctionHistoryConverter;

    /*
     * get all orders by seller id
     * 
     * @param sellerId, page, size
     * 
     * @return orders
     */
    @Override
    public List<LichSuDauGiaDTO> getOrders(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = auctionHistoryRepository.findOrdersBySellerId(sellerId, pageable);

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
    public List<LichSuDauGiaDTO> getOrdersByOrderStatus(long sellerId, String orderStatus, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = auctionHistoryRepository.findOrdersByOrderStatus(sellerId, orderStatus,
                pageable);

        if (page > entities.getTotalPages() || page <= 0) {
            throw new ResourceNotFoundException("No orders with page: " + page);
        }

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    private void checkExistedSeller(long sellerId) {
        boolean sellerExists = sellerRepository.existsByMaNguoiBan(sellerId);
        if (!sellerExists) {
            throw new ResourceNotFoundException("No seller with id: " + sellerId);
        }
    }

    @Override
    public List<LichSuDauGiaDTO> sortedAscByAuctionedPrice(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = auctionHistoryRepository.sortedAscByAuctionedPrice(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LichSuDauGiaDTO> sortedDescByAuctionedPrice(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = auctionHistoryRepository.sortedDescByAuctionedPrice(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LichSuDauGiaDTO> sortedAscByOrderDate(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = auctionHistoryRepository.sortedAscByOrderDate(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LichSuDauGiaDTO> sortedDescByOrderDate(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = auctionHistoryRepository.sortedDescByOrderDate(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LichSuDauGiaDTO> sortedAscByDeliveryDate(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = auctionHistoryRepository.sortedAscByDeliveryDate(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LichSuDauGiaDTO> sortedDescByDeliveryDate(long sellerId, int page, int size) {
        checkExistedSeller(sellerId);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<LichSuDauGiaEntity> entities = auctionHistoryRepository.sortedDescByDeliveryDate(sellerId, pageable);

        return entities.stream().map(auctionHistoryConverter::toDTO).collect(Collectors.toList());
    }

}
