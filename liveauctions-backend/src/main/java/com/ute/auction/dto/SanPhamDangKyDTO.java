package com.ute.auction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDangKyDTO {

    private Long maSanPhamDangKy;
    private String tenSanPham;
    private BigDecimal giaKhoiDiem;
    private String trangThaiDangKy;
    private LocalDateTime ngayDangKy;
    private String moTa;
    private String trangThaiXoa;
    private Long maNguoiBan;
    private Long maDanhMucCon;
    private Long maLoaiDauGia;

}
