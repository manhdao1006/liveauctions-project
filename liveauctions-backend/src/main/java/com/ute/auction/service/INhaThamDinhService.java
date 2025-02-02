package com.ute.auction.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.NhaThamDinhDTO;

public interface INhaThamDinhService {

    List<NhaThamDinhDTO> getAll(int page, int size);

    NhaThamDinhDTO getAppraiserById(long id);

    NhaThamDinhDTO getAppraiserByEmail(String email);

    NhaThamDinhDTO addAppraiser(NhaThamDinhDTO nhaThamDinhDTO, MultipartFile avatar) throws IOException;

    NhaThamDinhDTO updateAppraiser(long id, NhaThamDinhDTO nhaThamDinhDTO, MultipartFile avatar) throws IOException;

    void deleteAppraiser(long id);

    void banAppraiser(long id);

    List<NhaThamDinhDTO> searchAppraiser(String keyword, int page, int size);

    List<NhaThamDinhDTO> sortedAscByName(int page, int size);

    List<NhaThamDinhDTO> sortedDescByName(int page, int size);

    List<NhaThamDinhDTO> sortedAscByDoB(int page, int size);

    List<NhaThamDinhDTO> sortedDescByDoB(int page, int size);

}
