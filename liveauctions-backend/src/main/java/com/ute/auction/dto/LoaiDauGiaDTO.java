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
public class LoaiDauGiaDTO {

    private Long maLoaiDauGia;
    private String tenLoaiDauGia;
    private String trangThaiXoa;
    private List<SanPhamDangKyDTO> sanPhamDangKys;
    private List<PhienDauGiaDTO> phienDauGias;
    private List<SanPhamDTO> sanPhams;

}
