package com.ute.auction.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ute.auction.entity.impl.MaLichSuDauGia;

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
@Table(name = "lich_su_dau_gia")
public class LichSuDauGiaEntity {

    @EmbeddedId
    private MaLichSuDauGia maLichSuDauGia;

    @ManyToOne
    @MapsId("maPhienDauGia")
    @JoinColumn(name = "maPhienDauGia")
    private PhienDauGiaEntity phienDauGia;

    @ManyToOne
    @MapsId("maSanPham")
    @JoinColumn(name = "maSanPham")
    private SanPhamEntity sanPham;

    @ManyToOne
    @MapsId("maNguoiMua")
    @JoinColumn(name = "maNguoiMua")
    private NguoiMuaEntity nguoiMua;

    @ManyToOne
    @MapsId("maChiPhi")
    @JoinColumn(name = "maChiPhi")
    private ChiPhiEntity chiPhi;

    @Column(name = "giaDaDauGia")
    private BigDecimal giaDaDauGia;

    @Column(name = "thoiGianDauGia")
    private LocalDateTime thoiGianDauGia;

    @Column(name = "trangThaiDauGia")
    private String trangThaiDauGia;

    @Column(name = "ngayDatHang")
    private LocalDateTime ngayDatHang;

    @Column(name = "ngayGiaoHang")
    private LocalDateTime ngayGiaoHang;

    @Column(name = "trangThaiDonHang")
    private String trangThaiDonHang;

}
