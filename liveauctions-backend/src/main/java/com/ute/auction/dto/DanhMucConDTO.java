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
public class DanhMucConDTO {

    private Long maDanhMucCon;
    private String tenDanhMucCon;
    private Long maDanhMuc;
    private String trangThaiXoa;
    private List<SanPhamDangKyDTO> sanPhamDangKys;
    private List<SanPhamDTO> sanPhams;

}
