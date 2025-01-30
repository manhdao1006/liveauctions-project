package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.SanPhamDauGiaDTO;

public interface ISanPhamDauGiaService {

    List<SanPhamDauGiaDTO> getSanPhamDauGias();

    SanPhamDauGiaDTO addSanPhamDauGia(SanPhamDauGiaDTO sanPhamDauGiaDTO);

    SanPhamDauGiaDTO updateSanPhamDauGia(long maPhienDauGia, String maSanPham, SanPhamDauGiaDTO updatedSanPhamDauGia);

}
