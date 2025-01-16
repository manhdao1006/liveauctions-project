package com.ute.auction.entity;

import com.ute.auction.entity.impl.MaSanPhamDauGia;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sanpham_daugia")
public class SanPhamDauGiaEntity {

    @EmbeddedId
    private MaSanPhamDauGia maSanPhamDauGia;

    @ManyToOne
    @MapsId("maPhienDauGia")
    @JoinColumn(name = "maPhienDauGia")
    private PhienDauGiaEntity phienDauGia;

    @ManyToOne
    @MapsId("maSanPham")
    @JoinColumn(name = "maSanPham")
    private SanPhamEntity sanPham;

    @Column(name = "soLuongThamGia")
    private Long soLuongThamGia;

    @Column(name = "trangThai")
    private String trangThai;

}
