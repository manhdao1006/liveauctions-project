package com.ute.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDangKyResponseDTO {
    private SanPhamDangKyDTO sanPhamDangKy;
    private NguoiBanDTO nguoiBan;
    private DanhMucConDTO danhMucCon;
    private LoaiDauGiaDTO loaiDauGia;
}
