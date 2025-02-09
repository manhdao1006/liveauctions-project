package com.ute.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LichSuDauGiaResponseDTO {
    private LichSuDauGiaDTO lichSuDauGia;
    private PhienDauGiaDTO phienDauGia;
    private SanPhamDTO sanPham;
    private NguoiMuaDTO nguoiMua;
    private NguoiBanDTO nguoiBan;
    private ChiPhiDTO chiPhi;
}
