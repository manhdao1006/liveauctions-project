package com.ute.auction.service;

import com.ute.auction.dto.PageResponse;
import com.ute.auction.dto.SanPhamDauGiaDTO;
import com.ute.auction.dto.SanPhamDauGiaResponseDTO;

public interface ISanPhamDauGiaService {

    PageResponse<SanPhamDauGiaResponseDTO> getSanPhamDauGias(int page, int size);

    SanPhamDauGiaDTO addSanPhamDauGia(SanPhamDauGiaDTO sanPhamDauGiaDTO);

    SanPhamDauGiaDTO updateSanPhamDauGia(long maPhienDauGia, String maSanPham, SanPhamDauGiaDTO updatedSanPhamDauGia);

}
