package com.ute.auction.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.NguoiDungDTO;
import com.ute.auction.dto.NhanVienDTO;
import com.ute.auction.dto.NhanVienResponseDTO;

public interface INhanVienService {

        List<NhanVienResponseDTO> getNhanViens();

        NhanVienResponseDTO getNhanVienByMaNhanVien(long maNguoiDung);

        NhanVienResponseDTO addNhanVien(NguoiDungDTO nguoiDungDTO, NhanVienDTO nhanVienDTO, MultipartFile avatar)
                        throws IOException;

        NhanVienResponseDTO updateNhanVien(long maNguoiDung, NguoiDungDTO nguoiDungDTO, NhanVienDTO nhanVienDTO,
                        MultipartFile avatar)
                        throws IOException;

        void deleteNhanVien(long maNguoiDung);

        void banNhanVien(long maNguoiDung);
}
