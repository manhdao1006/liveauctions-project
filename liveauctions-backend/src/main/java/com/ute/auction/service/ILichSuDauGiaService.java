package com.ute.auction.service;

import com.ute.auction.dto.LichSuDauGiaDTO;
import com.ute.auction.dto.LichSuDauGiaResponseDTO;
import com.ute.auction.dto.PageResponse;

public interface ILichSuDauGiaService {

    PageResponse<LichSuDauGiaResponseDTO> getLichSuDauGiasByMaNguoiMua(long maNguoiMua, int page, int size);

    PageResponse<LichSuDauGiaResponseDTO> getLichSuDauGiasByMaNguoiBan(long maNguoiBan, int page, int size);

    LichSuDauGiaResponseDTO addLichSuDauGia(LichSuDauGiaDTO lichSuDauGiaDTO);

    LichSuDauGiaResponseDTO updateLichSuDauGia(long maPhienDauGia, String maSanPham, long maNguoiMua, long maChiPhi,
            LichSuDauGiaDTO lichSuDauGiaDTO);

    PageResponse<LichSuDauGiaResponseDTO> getByTrangThaiDonHangNguoiMua(long maNguoiMua, String trangThaiDonHang,
            int page, int size);

    PageResponse<LichSuDauGiaResponseDTO> getByTrangThaiDonHangNguoiBan(long maNguoiBan, String trangThaiDonHang,
            int page, int size);

}
