package com.ute.auction.service;

import java.util.List;

import com.ute.auction.dto.SanPhamDauGiaDTO;
import com.ute.auction.dto.SanPhamDauGiaResponseDTO;

public interface ISanPhamDauGiaService {

    List<SanPhamDauGiaResponseDTO> getSanPhamDauGias();

    SanPhamDauGiaDTO addSanPhamDauGia(SanPhamDauGiaDTO sanPhamDauGiaDTO);

    SanPhamDauGiaDTO updateSanPhamDauGia(long maPhienDauGia, String maSanPham, SanPhamDauGiaDTO updatedSanPhamDauGia);

    List<SanPhamDauGiaResponseDTO> getSanPhamDauGiasUpcoming();

    List<SanPhamDauGiaResponseDTO> getSanPhamDauGiasTrending();

    List<SanPhamDauGiaResponseDTO> getSanPhamDauGiasKin();

    List<SanPhamDauGiaResponseDTO> getSanPhamDauGiasOnline();

}
