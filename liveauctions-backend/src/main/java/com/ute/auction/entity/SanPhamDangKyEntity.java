package com.ute.auction.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sanpham_dangky")
public class SanPhamDangKyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maSanPhamDangKy")
    private Long maSanPhamDangKy;

    @Column(name = "tenSanPham")
    private String tenSanPham;

    @Column(name = "giaKhoiDiem")
    private BigDecimal giaKhoiDiem;

    @Column(name = "trangThaiDangKy")
    private String trangThaiDangKy;

    @Column(name = "ngayDangKy")
    private LocalDateTime ngayDangKy;

    @Column(name = "moTa", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "trangThaiXoa", nullable = false)
    private String trangThaiXoa = "1";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNguoiBan")
    private NguoiBanEntity nguoiBan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maDanhMucCon")
    private DanhMucConEntity danhMucCon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maLoaiDauGia")
    private LoaiDauGiaEntity loaiDauGia;

}
