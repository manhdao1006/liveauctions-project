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
import com.ute.auction.dto.ProductDTO;
import com.ute.auction.service.IProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController(value = "product" + ApiName.SEARCH_WEB)
@RequestMapping(ApiUrl.API_SELLER)
public class ProductController {

    private final IProductService productService;

    // Build API sorted asc starting price
    @GetMapping("/products/{id}/sorted-price-asc")
    public ResponseEntity<List<ProductDTO>> sortedAscByStartingPrice(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ProductDTO> models = productService.sortedAscByStartingPrice(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    // Build API sorted desc starting price
    @GetMapping("/products/{id}/sorted-price-desc")
    public ResponseEntity<List<ProductDTO>> sortedDescByStartingPrice(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ProductDTO> models = productService.sortedDescByStartingPrice(sellerId, page, size);
        return ResponseEntity.ok(models);
    }

    // Build API get products by seller id
    @GetMapping("/products/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsBySellerId(@PathVariable("id") int sellerId,
            @RequestParam("page") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ProductDTO> products = productService.getProductsBySellerId(sellerId, page, size);
        return ResponseEntity.ok(products);
    }

}
