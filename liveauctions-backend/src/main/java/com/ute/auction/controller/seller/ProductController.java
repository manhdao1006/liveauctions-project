package com.ute.auction.controller.seller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ute.auction.dto.ProductDTO;
import com.ute.auction.service.IProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/seller")
public class ProductController {

    private final IProductService productService;

    // Build API get products by seller id
    @GetMapping("/products/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsBySellerId(@PathVariable("id") Long sellerId, 
                                                                @RequestParam("page") int page, 
                                                                @RequestParam(defaultValue = "10") int size) {
        List<ProductDTO> products = productService.getProductsBySellerId(sellerId, page, size);
        return ResponseEntity.ok(products);
    }

}
