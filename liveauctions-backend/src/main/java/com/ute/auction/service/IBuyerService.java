package com.ute.auction.service;

import com.ute.auction.dto.BuyerDTO;

public interface IBuyerService {

    BuyerDTO getBuyerById(int id);

    BuyerDTO getBuyerByEmail(String email);

}
