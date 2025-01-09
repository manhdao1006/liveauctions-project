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
    private NguoiBanDTO nguoiBan;
    private DanhMucConDTO danhMucCon;
    private LoaiDauGiaDTO loaiDauGia;
    private NhaKhoDTO nhaKho;
    private NhaThamDinhDTO nhaThamDinh;
    private List<AnhSanPhamDTO> anhSanPhams;
    private List<SanPhamDauGiaDTO> sanPhamDauGias;
    private List<LichSuDauGiaDTO> lichSuDauGias;

}
