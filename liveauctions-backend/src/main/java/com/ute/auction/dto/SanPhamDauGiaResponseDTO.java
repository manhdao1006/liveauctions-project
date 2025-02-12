package com.ute.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDauGiaResponseDTO {
    private SanPhamDauGiaDTO sanPhamDauGia;
    private PhienDauGiaDTO phienDauGia;
    private SanPhamDTO sanPham;
}
