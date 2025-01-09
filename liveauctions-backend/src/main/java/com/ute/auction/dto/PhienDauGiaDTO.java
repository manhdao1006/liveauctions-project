package com.ute.auction.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhienDauGiaDTO {

    private Long maPhienDauGia;
    private String tenPhienDauGia;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private BigDecimal phiBaoHiem;
    private String trangThaiHoatDong;
    private String moTa;
    private String trangThaiXoa;
    private NhanVienDTO nhanVien;
    private LoaiDauGiaDTO loaiDauGia;
    private List<SanPhamDauGiaDTO> sanPhamDauGias;
    private List<PhieuDatCocDTO> phieuDatCocs;
    private List<LichSuDauGiaDTO> lichSuDauGias;

}
