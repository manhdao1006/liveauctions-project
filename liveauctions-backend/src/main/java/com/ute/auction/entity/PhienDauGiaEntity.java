package com.ute.auction.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phien_dau_gia")
public class PhienDauGiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maPhienDauGia")
    private Long maPhienDauGia;

    @Column(name = "tenPhienDauGia")
    private String tenPhienDauGia;

    @Column(name = "ngayBatDau")
    private LocalDate ngayBatDau;

    @Column(name = "ngayKetThuc")
    private LocalDate ngayKetThuc;

    @Column(name = "phiBaoHiem")
    private BigDecimal phiBaoHiem;

    @Column(name = "trangThaiHoatDong")
    private String trangThaiHoatDong;

    @Column(name = "moTa")
    private String moTa;

    @Column(name = "trangThaiXoa", nullable = false)
    private String trangThaiXoa = "1";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhanVien")
    private NhanVienEntity nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maLoaiDauGia")
    private LoaiDauGiaEntity loaiDauGia;

    @OneToMany(mappedBy = "phienDauGia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SanPhamDauGiaEntity> sanPhamDauGias = new ArrayList<>();

    @OneToMany(mappedBy = "phienDauGia")
    private List<PhieuDatCocEntity> phieuDatCocs = new ArrayList<>();

    @OneToMany(mappedBy = "phienDauGia")
    private List<LichSuDauGiaEntity> lichSuDauGias = new ArrayList<>();

}
