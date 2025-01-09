package com.ute.auction.dto;

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
public class NhaThamDinhDTO {

    private Long maNhaThamDinh;
    private String hoVaTen;
    private String email;
    private String gioiTinh;
    private String soDienThoai;
    private String diaChi;
    private String loai;
    private String trangThaiHoatDong;
    private String avatar;
    private LocalDate ngaySinh;
    private String moTa;
    private String trangThaiXoa;
    private List<SanPhamDTO> sanPhams;

}
