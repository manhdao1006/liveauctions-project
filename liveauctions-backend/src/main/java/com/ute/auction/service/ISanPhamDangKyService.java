package com.ute.auction.service;

import com.ute.auction.dto.PageResponse;
import com.ute.auction.dto.SanPhamDangKyDTO;
import com.ute.auction.dto.SanPhamDangKyResponseDTO;

public interface ISanPhamDangKyService {

    PageResponse<SanPhamDangKyResponseDTO> getSanPhamDangKys(int page, int size);

    SanPhamDangKyResponseDTO getSanPhamDangKyByMaSanPhamDangKy(long maSanPhamDangKy);

    PageResponse<SanPhamDangKyResponseDTO> getSanPhamDangKysByMaNguoiBan(long maNguoiBan, int page, int size);

    SanPhamDangKyDTO registerSanPham(SanPhamDangKyDTO sanPhamDangKyDTO);

}
