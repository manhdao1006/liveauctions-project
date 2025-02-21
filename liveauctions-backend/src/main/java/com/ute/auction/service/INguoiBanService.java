package com.ute.auction.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.NguoiBanDTO;
import com.ute.auction.dto.NguoiBanResponseDTO;
import com.ute.auction.dto.NguoiDungDTO;

public interface INguoiBanService {

    List<NguoiBanResponseDTO> getNguoiBans();

    NguoiBanResponseDTO getNguoiBanByMaNguoiBan(long maNguoiDung);

    NguoiBanResponseDTO addNguoiBan(NguoiDungDTO nguoiDungDTO, NguoiBanDTO nguoiBanDTO, MultipartFile avatar)
            throws IOException;

    NguoiBanResponseDTO updateNguoiBan(long maNguoiDung, NguoiDungDTO nguoiDungDTO, NguoiBanDTO nguoiBanDTO,
            MultipartFile avatar)
            throws IOException;

    void deleteNguoiBan(long maNguoiDung);

    void banNguoiBan(long maNguoiDung);

}
