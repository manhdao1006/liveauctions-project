package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.NhaThamDinhDTO;

public interface INhaThamDinhService {

    List<NhaThamDinhDTO> getAll(int page, int size);

    NhaThamDinhDTO getAppraiserById(long id);

    NhaThamDinhDTO getAppraiserByEmail(String email);

    NhaThamDinhDTO addAppraiser(NhaThamDinhDTO appraiser);

    NhaThamDinhDTO updateAppraiser(long id, NhaThamDinhDTO updatedAppraiser);

    void deleteAppraiser(long id);

    void banAppraiser(long id);

    List<NhaThamDinhDTO> searchAppraiser(String keyword, int page, int size);

    List<NhaThamDinhDTO> sortedAscByName(int page, int size);

    List<NhaThamDinhDTO> sortedDescByName(int page, int size);

    List<NhaThamDinhDTO> sortedAscByDoB(int page, int size);

    List<NhaThamDinhDTO> sortedDescByDoB(int page, int size);

}
