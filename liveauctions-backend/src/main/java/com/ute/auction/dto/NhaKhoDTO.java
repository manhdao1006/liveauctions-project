package com.ute.auction.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NhaKhoDTO {

    private Long maNhaKho;
    private String tenNhaKho;
    private String hoTenQuanLy;
    private String diaChi;
    private String trangThaiHoatDong;
    private String soLuongHienTai;
    private String soLuongToiDa;
    private String ngayHoatDong;
    private String trangThaiXoa;
    private PhuongXaDTO phuongXa;
    private List<SanPhamDTO> sanPhams;

}
