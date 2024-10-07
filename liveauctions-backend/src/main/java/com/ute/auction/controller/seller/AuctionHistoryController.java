package com.ute.auction.controller.seller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.dto.AuctionHistoryDTO;
import com.ute.auction.service.IAuctionHistoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/seller")
public class AuctionHistoryController {

    private final IAuctionHistoryService auctionHistoryService;

    @GetMapping("/orders/{id}/sorted-price-asc")
    public ResponseEntity<List<AuctionHistoryDTO>> sortedAscByAuctionedPrice(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AuctionHistoryDTO> models = auctionHistoryService.sortedAscByAuctionedPrice(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/orders/{id}/sorted-price-desc")
    public ResponseEntity<List<AuctionHistoryDTO>> sortedDescByAuctionedPrice(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AuctionHistoryDTO> models = auctionHistoryService.sortedDescByAuctionedPrice(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/orders/{id}/sorted-order-date-asc")
    public ResponseEntity<List<AuctionHistoryDTO>> sortedAscByOrderDate(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AuctionHistoryDTO> models = auctionHistoryService.sortedAscByOrderDate(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/orders/{id}/sorted-order-date-desc")
    public ResponseEntity<List<AuctionHistoryDTO>> sortedDescByOrderDate(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AuctionHistoryDTO> models = auctionHistoryService.sortedDescByOrderDate(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/orders/{id}/sorted-delivery-date-asc")
    public ResponseEntity<List<AuctionHistoryDTO>> sortedAscByDeliveryDate(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AuctionHistoryDTO> models = auctionHistoryService.sortedAscByDeliveryDate(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/orders/{id}/sorted-delivery-date-desc")
    public ResponseEntity<List<AuctionHistoryDTO>> sortedDescByDeliveryDate(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AuctionHistoryDTO> models = auctionHistoryService.sortedDescByDeliveryDate(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    // Build API get orders by seller id
    @GetMapping("/order-history/{id}")
    public ResponseEntity<List<AuctionHistoryDTO>> getOrders(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AuctionHistoryDTO> orders = auctionHistoryService.getOrders(sellerId, page, size);
        return ResponseEntity.ok(orders);
    }

    // Build API get orders by status
    @GetMapping("/order-history/{id}/filter")
    public ResponseEntity<List<AuctionHistoryDTO>> getOrdersByOrderStatus(@PathVariable("id") int sellerId,
            @RequestParam("orderStatus") String orderStatus,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AuctionHistoryDTO> orders = auctionHistoryService.getOrdersByOrderStatus(sellerId, orderStatus, page,
                size);
        return ResponseEntity.ok(orders);
    }

}
