package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.RegistrationProductDTO;

public interface IRegistrationProductService {

    List<RegistrationProductDTO> getRegistrationProductsBySellerId(Long sellerId, int page, int size);

    RegistrationProductDTO registerProduct(RegistrationProductDTO regisProduct);

    List<RegistrationProductDTO> sortedAscByStartingPrice(Long sellerId, int page, int size);

    List<RegistrationProductDTO> sortedDescByStartingPrice(Long sellerId, int page, int size);

    List<RegistrationProductDTO> sortedAscByRegistrationDate(Long sellerId, int page, int size);

    List<RegistrationProductDTO> sortedDescByRegistrationDate(Long sellerId, int page, int size);
    
}
