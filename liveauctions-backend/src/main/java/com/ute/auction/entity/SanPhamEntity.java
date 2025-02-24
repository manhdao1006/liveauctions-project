package com.ute.auction.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "san_pham")
public class SanPhamEntity {

    @Id
    @Column(name = "maSanPham")
    private String maSanPham;

    @Column(name = "tenSanPham")
    private String tenSanPham;

    @Column(name = "giaKhoiDiem")
    private BigDecimal giaKhoiDiem;

    @Column(name = "trangThai")
    private String trangThai;

    @Column(name = "giaNhoNhat")
    private BigDecimal giaNhoNhat;

    @Column(name = "giaLonNhat")
    private BigDecimal giaLonNhat;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhaKho")
    private NhaKhoEntity nhaKho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhaThamDinh")
    private NhaThamDinhEntity nhaThamDinh;

    @OneToMany(mappedBy = "sanPham", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private List<AnhSanPhamEntity> anhSanPhams = new ArrayList<>();

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SanPhamDauGiaEntity> sanPhamDauGias = new ArrayList<>();

    @OneToMany(mappedBy = "sanPham")
    private List<LichSuDauGiaEntity> lichSuDauGias = new ArrayList<>();

}
