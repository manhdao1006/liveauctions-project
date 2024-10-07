package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.AuctionHistoryDTO;

public interface IAuctionHistoryService {

    List<AuctionHistoryDTO> getOrders(int sellerId, int page, int size);

    List<AuctionHistoryDTO> getOrdersByOrderStatus(int sellerId, String orderStatus, int page, int size);

    List<AuctionHistoryDTO> sortedAscByAuctionedPrice(int sellerId, int page, int size);

    List<AuctionHistoryDTO> sortedDescByAuctionedPrice(int sellerId, int page, int size);

    List<AuctionHistoryDTO> sortedAscByOrderDate(int sellerId, int page, int size);

    List<AuctionHistoryDTO> sortedDescByOrderDate(int sellerId, int page, int size);

    List<AuctionHistoryDTO> sortedAscByDeliveryDate(int sellerId, int page, int size);

    List<AuctionHistoryDTO> sortedDescByDeliveryDate(int sellerId, int page, int size);

}
