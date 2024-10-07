package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.ProductDTO;

public interface IProductService {

    List<ProductDTO> getProductsBySellerId(int sellerId, int page, int size);

    List<ProductDTO> sortedAscByStartingPrice(int sellerId, int page, int size);

    List<ProductDTO> sortedDescByStartingPrice(int sellerId, int page, int size);

}
