package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.RegistrationProductDTO;

public interface IRegistrationProductService {

    List<RegistrationProductDTO> getRegistrationProductsBySellerId(Long sellerId, int page, int size);

    RegistrationProductDTO registerProduct(RegistrationProductDTO regisProduct);
    
}
