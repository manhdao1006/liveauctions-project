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
public class NhanVienDTO {

    private Long maNhanVien;
    private String viTri;
    private String trangThaiXoa;
    private Long maNguoiDung;
    private List<PhienDauGiaDTO> phienDauGias;

}
