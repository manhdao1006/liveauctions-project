package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.RegistrationProductDTO;

public interface IRegistrationProductService {

    List<RegistrationProductDTO> getRegistrationProductsBySellerId(int sellerId, int page, int size);

    RegistrationProductDTO registerProduct(RegistrationProductDTO regisProduct);

    List<RegistrationProductDTO> sortedAscByStartingPrice(int sellerId, int page, int size);

    List<RegistrationProductDTO> sortedDescByStartingPrice(int sellerId, int page, int size);

    List<RegistrationProductDTO> sortedAscByRegistrationDate(int sellerId, int page, int size);

    List<RegistrationProductDTO> sortedDescByRegistrationDate(int sellerId, int page, int size);

}
