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
    private String trangThaiConTrong;
    private String ngayHoatDong;
    private String trangThaiXoa;
    private Long maPhuongXa;
    private List<SanPhamDTO> sanPhams;

}
