package com.ute.auction.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.NguoiMuaDTO;
import com.ute.auction.dto.NguoiMuaResponseDTO;
import com.ute.auction.dto.PageResponse;

public interface INguoiMuaService {

    PageResponse<NguoiMuaResponseDTO> getNguoiMuas(int page, int size);

    NguoiMuaResponseDTO getNguoiMuaByMaNguoiMua(long maNguoiDung);

    NguoiMuaResponseDTO addNguoiMua(NguoiDungDTO nguoiDungDTO, NguoiMuaDTO nguoiMuaDTO, MultipartFile avatar)
            throws IOException;

    NguoiMuaResponseDTO updateNguoiMua(long maNguoiDung, NguoiDungDTO nguoiDungDTO, NguoiMuaDTO nguoiMuaDTO,
            MultipartFile avatar)
            throws IOException;

    void deleteNguoiMua(long maNguoiDung);

    void banNguoiMua(long maNguoiDung);

}
