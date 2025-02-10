package com.ute.auction.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NguoiMuaDTO {

    private Long maNguoiMua;
    private LocalDateTime ngayDangKy;
    private String tenTaiKhoan;
    private String soThe;
    private String nganHang;
    private LocalDateTime ngayHetHan;
    private String cvv;
    private String diaChiThanhToan;
    private String trangThaiXoa;
    private Long maNguoiDung;
    private List<PhieuDatCocDTO> phieuDatCocs;
    private List<LichSuDauGiaDTO> lichSuDauGias;

}
