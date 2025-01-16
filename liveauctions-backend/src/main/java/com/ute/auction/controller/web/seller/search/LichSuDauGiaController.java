package com.ute.auction.controller.web.seller.search;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.constant.ApiName;
import com.ute.auction.constant.ApiUrl;
import com.ute.auction.dto.LichSuDauGiaDTO;
import com.ute.auction.service.ILichSuDauGiaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "auctionHistory" + ApiName.SEARCH_WEB)
@RequestMapping(ApiUrl.API_SELLER)
public class LichSuDauGiaController {

    private final ILichSuDauGiaService auctionHistoryService;

    @GetMapping("/orders/{id}/sorted-price-asc")
    public ResponseEntity<List<LichSuDauGiaDTO>> sortedAscByAuctionedPrice(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<LichSuDauGiaDTO> models = auctionHistoryService.sortedAscByAuctionedPrice(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/orders/{id}/sorted-price-desc")
    public ResponseEntity<List<LichSuDauGiaDTO>> sortedDescByAuctionedPrice(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<LichSuDauGiaDTO> models = auctionHistoryService.sortedDescByAuctionedPrice(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/orders/{id}/sorted-order-date-asc")
    public ResponseEntity<List<LichSuDauGiaDTO>> sortedAscByOrderDate(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<LichSuDauGiaDTO> models = auctionHistoryService.sortedAscByOrderDate(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/orders/{id}/sorted-order-date-desc")
    public ResponseEntity<List<LichSuDauGiaDTO>> sortedDescByOrderDate(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<LichSuDauGiaDTO> models = auctionHistoryService.sortedDescByOrderDate(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/orders/{id}/sorted-delivery-date-asc")
    public ResponseEntity<List<LichSuDauGiaDTO>> sortedAscByDeliveryDate(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<LichSuDauGiaDTO> models = auctionHistoryService.sortedAscByDeliveryDate(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/orders/{id}/sorted-delivery-date-desc")
    public ResponseEntity<List<LichSuDauGiaDTO>> sortedDescByDeliveryDate(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<LichSuDauGiaDTO> models = auctionHistoryService.sortedDescByDeliveryDate(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    // Build API get orders by seller id
    @GetMapping("/order-history/{id}")
    public ResponseEntity<List<LichSuDauGiaDTO>> getOrders(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<LichSuDauGiaDTO> orders = auctionHistoryService.getOrders(sellerId, page, size);
        return ResponseEntity.ok(orders);
    }

    // Build API get orders by status
    @GetMapping("/order-history/{id}/filter")
    public ResponseEntity<List<LichSuDauGiaDTO>> getOrdersByOrderStatus(@PathVariable("id") int sellerId,
            @RequestParam("orderStatus") String orderStatus,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<LichSuDauGiaDTO> orders = auctionHistoryService.getOrdersByOrderStatus(sellerId, orderStatus, page,
                size);
        return ResponseEntity.ok(orders);
    }

}
