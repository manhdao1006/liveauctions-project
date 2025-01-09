package com.ute.auction.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LichSuDauGiaDTO {

    private PhienDauGiaDTO phienDauGia;
    private SanPhamDTO sanPham;
    private NguoiMuaDTO nguoiMua;
    private ChiPhiDTO chiPhi;
    private BigDecimal giaDaDauGia;
    private LocalDate thoiGianDauGia;
    private String trangThaiDauGia;
    private LocalDate ngayDatHang;
    private LocalDate ngayGiaoHang;
    private String trangThaiDonHang;

}
