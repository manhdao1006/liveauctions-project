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
public class NguoiDungDTO {

    private Long maNguoiDung;
    private String hoVaTen;
    private String email;
    private String matKhau;
    private String soDienThoai;
    private String diaChi;
    private String trangThaiHoatDong;
    private String avatar;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String trangThaiXoa;
    private List<VaiTroDTO> vaitros;
    private PhuongXaDTO phuongXa;
    private NguoiMuaDTO nguoiMua;
    private NguoiBanDTO nguoiBan;
    private NhanVienDTO nhanVien;

}
