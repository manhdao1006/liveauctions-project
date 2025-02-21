package com.ute.auction.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.PageResponse;
import com.ute.auction.dto.SanPhamDTO;
import com.ute.auction.dto.SanPhamResponseDTO;

public interface ISanPhamService {

    List<SanPhamResponseDTO> getSanPhams();

    PageResponse<SanPhamResponseDTO> getSanPhamsByMaNguoiBan(long maNguoiBan, int page, int size);

    SanPhamDTO addSanPham(SanPhamDTO sanPhamDTO, List<MultipartFile> anhSanPhamList) throws IOException;

    SanPhamDTO updateSanPham(String maSanPham, SanPhamDTO sanPhamDTO, List<MultipartFile> anhSanPhamList,
            List<String> deletedImageNames)
            throws IOException;

    void deleteSanPham(String maSanPham);

    SanPhamResponseDTO getSanPhamByMaSanPham(String maSanPham);

}
