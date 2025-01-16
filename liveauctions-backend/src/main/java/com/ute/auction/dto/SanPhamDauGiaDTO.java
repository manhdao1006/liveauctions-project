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

    private Long maPhienDauGia;
    private String maSanPham;
    private Long soLuongThamGia;
    private String trangThai;

}
