package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.ProductDTO;

public interface IProductService {

    List<ProductDTO> getProductsBySellerId(Long sellerId, int page, int size);
    
}
