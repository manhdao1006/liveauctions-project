package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.AppraiserDTO;

public interface IAppraiserService {

    List<AppraiserDTO> getAll(int page, int size);

    AppraiserDTO getAppraiserById(Long id);

    AppraiserDTO getAppraiserByEmail(String email);

    AppraiserDTO addAppraiser(AppraiserDTO appraiser);

    AppraiserDTO updateAppraiser(Long id, AppraiserDTO updatedAppraiser);

    void deleteAppraiser(Long id);

    void banAppraiser(Long id);

    List<AppraiserDTO> searchAppraiser(String keyword, int page, int size);

}
