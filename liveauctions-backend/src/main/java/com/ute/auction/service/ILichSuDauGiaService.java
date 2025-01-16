package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.LichSuDauGiaDTO;

public interface ILichSuDauGiaService {

    List<LichSuDauGiaDTO> getOrders(long sellerId, int page, int size);

    List<LichSuDauGiaDTO> getOrdersByOrderStatus(long sellerId, String orderStatus, int page, int size);

    List<LichSuDauGiaDTO> sortedAscByAuctionedPrice(long sellerId, int page, int size);

    List<LichSuDauGiaDTO> sortedDescByAuctionedPrice(long sellerId, int page, int size);

    List<LichSuDauGiaDTO> sortedAscByOrderDate(long sellerId, int page, int size);

    List<LichSuDauGiaDTO> sortedDescByOrderDate(long sellerId, int page, int size);

    List<LichSuDauGiaDTO> sortedAscByDeliveryDate(long sellerId, int page, int size);

    List<LichSuDauGiaDTO> sortedDescByDeliveryDate(long sellerId, int page, int size);

}
