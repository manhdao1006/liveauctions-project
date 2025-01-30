package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.SanPhamDangKyDTO;

public interface ISanPhamDangKyService {

    List<SanPhamDangKyDTO> getSanPhamDangKys();

    SanPhamDangKyDTO getSanPhamDangKyByMaSanPhamDangKy(long maSanPhamDangKy);

    List<SanPhamDangKyDTO> getRegistrationProductsBySellerId(long sellerId, int page, int size);

    SanPhamDangKyDTO registerProduct(SanPhamDangKyDTO regisProduct);

    List<SanPhamDangKyDTO> sortedAscByStartingPrice(long sellerId, int page, int size);

    List<SanPhamDangKyDTO> sortedDescByStartingPrice(long sellerId, int page, int size);

    List<SanPhamDangKyDTO> sortedAscByRegistrationDate(long sellerId, int page, int size);

    List<SanPhamDangKyDTO> sortedDescByRegistrationDate(long sellerId, int page, int size);

}
