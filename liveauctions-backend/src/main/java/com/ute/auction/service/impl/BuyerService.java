package com.ute.auction.service.impl;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.BuyerConverter;
import com.ute.auction.dto.BuyerDTO;
import com.ute.auction.entity.BuyerEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.BuyerRepository;
import com.ute.auction.service.IBuyerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuyerService implements IBuyerService {

    private final BuyerRepository buyerRepository;
    private final BuyerConverter buyerConverter;

    /*
     * get buyer by id
     * 
     * @param id
     * 
     * @return buyer
     */
    @Override
    public BuyerDTO getBuyerById(int id) {
        BuyerEntity buyerEntity = buyerRepository.findByBuyerId(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        BuyerDTO buyerDTO = buyerConverter.toDTO(buyerEntity);
        return buyerDTO;
    }

    /*
     * get buyer by email
     * 
     * @param email
     * 
     * @return buyer
     */
    @Override
    public BuyerDTO getBuyerByEmail(String email) {
        BuyerEntity buyerEntity = buyerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        BuyerDTO buyerDTO = buyerConverter.toDTO(buyerEntity);
        return buyerDTO;
    }

}
