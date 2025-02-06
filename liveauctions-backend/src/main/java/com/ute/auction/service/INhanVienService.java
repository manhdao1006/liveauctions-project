package com.ute.auction.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.NhanVienDTO;
import com.ute.auction.dto.NhanVienResponseDTO;
import com.ute.auction.dto.PageResponse;

public interface INhanVienService {

        PageResponse<NhanVienResponseDTO> getNhanViens(int page, int size);

        NhanVienResponseDTO getNhanVienByMaNhanVien(long maNguoiDung);

        NhanVienResponseDTO addNhanVien(NguoiDungDTO nguoiDungDTO, NhanVienDTO nhanVienDTO, MultipartFile avatar)
                        throws IOException;

        NhanVienResponseDTO updateNhanVien(long maNguoiDung, NguoiDungDTO nguoiDungDTO, NhanVienDTO nhanVienDTO,
                        MultipartFile avatar)
                        throws IOException;

        void deleteNhanVien(long maNguoiDung);

        void banNhanVien(long maNguoiDung);
}
