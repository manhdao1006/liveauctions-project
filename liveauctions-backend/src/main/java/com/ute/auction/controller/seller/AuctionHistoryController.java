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

    // Build API get orders by seller id
    @GetMapping("/order-history/{id}")
    public ResponseEntity<List<AuctionHistoryDTO>> getOrders(@PathVariable("id") Long sellerId, 
                                                            @RequestParam("page") int page, 
                                                            @RequestParam(defaultValue = "10") int size) {
        List<AuctionHistoryDTO> orders = auctionHistoryService.getOrders(sellerId, page, size);
        return ResponseEntity.ok(orders);
    }

    // Build API get orders by status
    @GetMapping("/order-history/{id}/filter")
    public ResponseEntity<List<AuctionHistoryDTO>> getOrdersByOrderStatus(@PathVariable("id") Long sellerId, 
                                                                        @RequestParam("orderStatus") String orderStatus, 
                                                                        @RequestParam("page") int page, 
                                                                        @RequestParam(defaultValue = "10") int size) {
        List<AuctionHistoryDTO> orders = auctionHistoryService.getOrdersByOrderStatus(sellerId, orderStatus, page, size);
        return ResponseEntity.ok(orders);
    }

}
