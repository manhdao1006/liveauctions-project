package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.SanPhamDTO;

public interface ISanPhamService {

    List<SanPhamDTO> getProductsBySellerId(long sellerId, int page, int size);

    List<SanPhamDTO> sortedAscByStartingPrice(long sellerId, int page, int size);

    List<SanPhamDTO> sortedDescByStartingPrice(long sellerId, int page, int size);

    List<SanPhamDTO> getSanPhams();

    SanPhamDTO addSanPham(SanPhamDTO sanPhamDTO);

    SanPhamDTO updateSanPham(String maSanPham, SanPhamDTO updatedSanPham);

    void deleteSanPham(String maSanPham);

}
