package com.ute.auction.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nguoi_mua")
public class NguoiMuaEntity {

    @Id
    @Column(name = "maNguoiMua")
    private Long maNguoiMua;

    @Column(name = "ngayDangKy")
    private LocalDate ngayDangKy;

    @Column(name = "tenTaiKhoan")
    private String tenTaiKhoan;

    @Column(name = "soThe")
    private String soThe;

    @Column(name = "nganHang")
    private String nganHang;

    @Column(name = "ngayHetHan")
    private LocalDate ngayHetHan;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "diaChiThanhToan")
    private String diaChiThanhToan;

    @Column(name = "trangThaiXoa", nullable = false)
    private String trangThaiXoa = "1";

    @MapsId
    @OneToOne
    @JoinColumn(name = "maNguoiMua", referencedColumnName = "maNguoiDung")
    private NguoiDungEntity nguoiDung;

    @OneToMany(mappedBy = "nguoiMua")
    private List<PhieuDatCocEntity> phieuDatCocs = new ArrayList<>();

    @OneToMany(mappedBy = "nguoiMua")
    private List<LichSuDauGiaEntity> lichSuDauGias = new ArrayList<>();

}
