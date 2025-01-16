package com.ute.auction.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loai_dau_gia")
public class LoaiDauGiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maLoaiDauGia")
    private Long maLoaiDauGia;

    @Column(name = "tenLoaiDauGia")
    private String tenLoaiDauGia;

    @Column(name = "trangThaiXoa", nullable = false)
    private String trangThaiXoa = "1";

    @OneToMany(mappedBy = "loaiDauGia")
    private List<SanPhamDangKyEntity> sanPhamDangKys = new ArrayList<>();

    @OneToMany(mappedBy = "loaiDauGia")
    private List<PhienDauGiaEntity> phienDauGias = new ArrayList<>();

    @OneToMany(mappedBy = "loaiDauGia")
    private List<SanPhamEntity> sanPhams = new ArrayList<>();

}
