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
import com.ute.auction.dto.SanPhamDangKyDTO;
import com.ute.auction.service.ISanPhamDangKyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "registrationProduct" + ApiName.SEARCH_WEB)
@RequestMapping(ApiUrl.API_SELLER)
public class SanPhamDangKyController {

        private final ISanPhamDangKyService registrationProductService;

        // Build API sorted asc starting price
        @GetMapping("/registration-products/{id}/sorted-price-asc")
        public ResponseEntity<List<SanPhamDangKyDTO>> sortedAscByStartingPrice(@PathVariable("id") int sellerId,
                        @RequestParam("page") int page,
                        @RequestParam(defaultValue = "10") int size) {
                List<SanPhamDangKyDTO> models = registrationProductService.sortedAscByStartingPrice(sellerId,
                                page, size);
                return ResponseEntity.ok(models);
        }

        // Build API sorted desc starting price
        @GetMapping("/registration-products/{id}/sorted-price-desc")
        public ResponseEntity<List<SanPhamDangKyDTO>> sortedDescByStartingPrice(@PathVariable("id") int sellerId,
                        @RequestParam("page") int page,
                        @RequestParam(defaultValue = "10") int size) {
                List<SanPhamDangKyDTO> models = registrationProductService.sortedDescByStartingPrice(sellerId,
                                page,
                                size);
                return ResponseEntity.ok(models);
        }

        // Build API sorted asc registration date
        @GetMapping("/registration-products/{id}/sorted-date-asc")
        public ResponseEntity<List<SanPhamDangKyDTO>> sortedAscByRegistrationDate(
                        @PathVariable("id") int sellerId,
                        @RequestParam("page") int page,
                        @RequestParam(defaultValue = "10") int size) {
                List<SanPhamDangKyDTO> models = registrationProductService.sortedAscByRegistrationDate(sellerId,
                                page,
                                size);
                return ResponseEntity.ok(models);
        }

        // Build API sorted desc registration date
        @GetMapping("/registration-products/{id}/sorted-date-desc")
        public ResponseEntity<List<SanPhamDangKyDTO>> sortedDescByRegistrationDate(
                        @PathVariable("id") int sellerId,
                        @RequestParam("page") int page,
                        @RequestParam(defaultValue = "10") int size) {
                List<SanPhamDangKyDTO> models = registrationProductService.sortedDescByRegistrationDate(sellerId,
                                page,
                                size);
                return ResponseEntity.ok(models);
        }

        // Build API get registration products by seller id
        @GetMapping("/registration-products/{id}")
        public ResponseEntity<List<SanPhamDangKyDTO>> getRegistrationProductsBySellerId(
                        @PathVariable("id") int sellerId,
                        @RequestParam("page") int page,
                        @RequestParam(defaultValue = "10") int size) {
                List<SanPhamDangKyDTO> registrationProducts = registrationProductService
                                .getRegistrationProductsBySellerId(sellerId, page, size);
                return ResponseEntity.ok(registrationProducts);
        }

}
