package com.ute.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDauGiaDTO {

    private PhienDauGiaDTO phienDauGia;
    private SanPhamDTO sanPham;
    private Long soLuongThamGia;
    private String trangThai;

}
