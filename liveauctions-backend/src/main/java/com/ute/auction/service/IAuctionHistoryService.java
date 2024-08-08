package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.AuctionHistoryDTO;

public interface IAuctionHistoryService {

    List<AuctionHistoryDTO> getOrders(Long sellerId, int page, int size);

    List<AuctionHistoryDTO> getOrdersByOrderStatus(Long sellerId, String orderStatus, int page, int size);

}
