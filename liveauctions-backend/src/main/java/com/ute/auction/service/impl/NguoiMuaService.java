package com.ute.auction.service.impl;

import org.springframework.stereotype.Service;

import com.ute.auction.converter.NguoiMuaConverter;
import com.ute.auction.dto.NguoiMuaDTO;
import com.ute.auction.entity.NguoiMuaEntity;
import com.ute.auction.exception.ResourceNotFoundException;
import com.ute.auction.repository.NguoiMuaRepository;
import com.ute.auction.service.INguoiMuaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NguoiMuaService implements INguoiMuaService {

    private final NguoiMuaRepository buyerRepository;
    private final NguoiMuaConverter buyerConverter;

    /*
     * get buyer by id
     * 
     * @param id
     * 
     * @return buyer
     */
    @Override
    public NguoiMuaDTO getNguoiMuaByMaNguoiMua(long id) {
        NguoiMuaEntity buyerEntity = buyerRepository.findByMaNguoiDung(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        NguoiMuaDTO buyerDTO = buyerConverter.toDTO(buyerEntity);
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
    public NguoiMuaDTO getNguoiMuaByEmail(String email) {
        NguoiMuaEntity buyerEntity = buyerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        NguoiMuaDTO buyerDTO = buyerConverter.toDTO(buyerEntity);
        return buyerDTO;
    }

}
