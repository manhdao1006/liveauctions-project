package com.ute.auction.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "nguoi_ban")
public class NguoiBanEntity {

    @Id
    @Column(name = "maNguoiBan")
    private Long maNguoiBan;

    @Column(name = "maSoThue")
    private String maSoThue;

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
    @JoinColumn(name = "maNguoiBan", referencedColumnName = "id")
    private NguoiDungEntity nguoiDung;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "thue_nguoiban", joinColumns = @JoinColumn(name = "maNguoiBan"), inverseJoinColumns = @JoinColumn(name = "maThue"))
    private List<ThueEntity> thues = new ArrayList<>();

    @OneToMany(mappedBy = "nguoiBan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SanPhamDangKyEntity> sanPhamDangKys = new ArrayList<>();

    @OneToMany(mappedBy = "nguoiBan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SanPhamEntity> sanPhams = new ArrayList<>();

}
