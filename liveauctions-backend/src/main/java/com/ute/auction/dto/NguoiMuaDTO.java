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
public class NguoiMuaDTO {

    private Long maNguoiMua;
    private LocalDate ngayDangKy;
    private String tenTaiKhoan;
    private String soThe;
    private String nganHang;
    private LocalDate ngayHetHan;
    private String cvv;
    private String diaChiThanhToan;
    private String trangThaiXoa;
    private NguoiDungDTO nguoiDung;
    private List<PhieuDatCocDTO> phieuDatCocs;
    private List<LichSuDauGiaDTO> lichSuDauGias;

}
