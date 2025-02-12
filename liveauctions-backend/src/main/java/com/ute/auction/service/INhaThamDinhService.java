package com.ute.auction.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.NhaThamDinhDTO;

public interface INhaThamDinhService {

    List<NhaThamDinhDTO> getNhaThamDinhs();

    NhaThamDinhDTO getNhaThamDinhByMaNhaThamDinh(long maNhaThamDinh);

    NhaThamDinhDTO getNhaThamDinhByEmail(String email);

    NhaThamDinhDTO addNhaThamDinh(NhaThamDinhDTO nhaThamDinhDTO, MultipartFile avatar) throws IOException;

    NhaThamDinhDTO updateNhaThamDinh(long maNhaThamDinh, NhaThamDinhDTO nhaThamDinhDTO, MultipartFile avatar)
            throws IOException;

    void deleteNhaThamDinh(long maNhaThamDinh);

    void banNhaThamDinh(long maNhaThamDinh);

    List<NhaThamDinhDTO> searchNhaThamDinh(String keyword, int page, int size);

}
