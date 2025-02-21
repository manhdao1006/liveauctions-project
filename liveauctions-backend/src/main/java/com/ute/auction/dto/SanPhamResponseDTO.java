package com.ute.auction.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamResponseDTO {
    private SanPhamDTO sanPham;
    private NguoiBanDTO nguoiBan;
    private DanhMucConDTO danhMucCon;
    private LoaiDauGiaDTO loaiDauGia;
    private NhaKhoDTO nhaKho;
    private NhaThamDinhDTO nhaThamDinh;
    private List<AnhSanPhamDTO> anhSanPhams;
}
