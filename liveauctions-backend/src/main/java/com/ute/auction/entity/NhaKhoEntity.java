package com.ute.auction.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "nha_kho")
public class NhaKhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maNhaKho")
    private Long maNhaKho;

    @Column(name = "tenNhaKho")
    private String tenNhaKho;

    @Column(name = "hoTenQuanLy")
    private String hoTenQuanLy;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "trangThaiHoatDong")
    private String trangThaiHoatDong;

    @Column(name = "trangThaiConTrong")
    private String trangThaiConTrong;

    @Column(name = "ngayHoatDong")
    private String ngayHoatDong;

    @Column(name = "trangThaiXoa", nullable = false)
    private String trangThaiXoa = "1";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhuongXa")
    private PhuongXaEntity phuongXa;

    @OneToMany(mappedBy = "nhaKho")
    private List<SanPhamEntity> sanPhams = new ArrayList<>();

}
