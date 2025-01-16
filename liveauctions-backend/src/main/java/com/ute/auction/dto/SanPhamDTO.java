package com.ute.auction.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDTO {

    private String maSanPham;
    private String tenSanPham;
    private BigDecimal giaKhoiDiem;
    private String trangThai;
    private BigDecimal giaNhoNhat;
    private BigDecimal giaLonNhat;
    private String moTa;
    private String trangThaiXoa;
    private Long maNguoiBan;
    private Long maDanhMucCon;
    private Long maLoaiDauGia;
    private Long maNhaKho;
    private Long maNhaThamDinh;
    private List<AnhSanPhamDTO> anhSanPhams;
    private List<SanPhamDauGiaDTO> sanPhamDauGias;
    private List<LichSuDauGiaDTO> lichSuDauGias;

}
