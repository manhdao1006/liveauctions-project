package com.ute.auction.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ute.auction.dto.SanPhamDTO;

public interface ISanPhamService {

    List<SanPhamDTO> getProductsBySellerId(long sellerId, int page, int size);

    List<SanPhamDTO> sortedAscByStartingPrice(long sellerId, int page, int size);

    List<SanPhamDTO> sortedDescByStartingPrice(long sellerId, int page, int size);

    List<SanPhamDTO> getSanPhams();

    SanPhamDTO addSanPham(SanPhamDTO sanPhamDTO, List<MultipartFile> anhSanPhamList) throws IOException;

    SanPhamDTO updateSanPham(String maSanPham, SanPhamDTO sanPhamDTO, List<MultipartFile> anhSanPhamList)
            throws IOException;

    void deleteSanPham(String maSanPham);

}
