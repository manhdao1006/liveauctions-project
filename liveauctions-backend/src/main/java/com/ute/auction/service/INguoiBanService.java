package com.ute.auction.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.NguoiBanDTO;
import com.ute.auction.dto.NguoiBanResponseDTO;
import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.PageResponse;

public interface INguoiBanService {

        PageResponse<NguoiBanResponseDTO> getNguoiBans(int page, int size);

        NguoiBanResponseDTO getNguoiBanByMaNguoiBan(long maNguoiDung);

        NguoiBanResponseDTO addNguoiBan(NguoiDungDTO nguoiDungDTO, NguoiBanDTO nguoiBanDTO, MultipartFile avatar)
                        throws IOException;

        NguoiBanResponseDTO updateNguoiBan(long maNguoiDung, NguoiDungDTO nguoiDungDTO, NguoiBanDTO nguoiBanDTO,
                        MultipartFile avatar)
                        throws IOException;

        void deleteNguoiBan(long maNguoiDung);

        void banNguoiBan(long maNguoiDung);

}
