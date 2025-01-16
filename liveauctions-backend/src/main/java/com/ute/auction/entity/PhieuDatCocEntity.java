package com.ute.auction.entity;

import java.math.BigDecimal;

import com.ute.auction.entity.impl.MaPhieuDatCoc;

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
@Table(name = "phieu_dat_coc")
public class PhieuDatCocEntity {

    @EmbeddedId
    private MaPhieuDatCoc maPhieuDatCoc;

    @ManyToOne
    @MapsId("maPhienDauGia")
    @JoinColumn(name = "maPhienDauGia")
    private PhienDauGiaEntity phienDauGia;

    @ManyToOne
    @MapsId("maNguoiMua")
    @JoinColumn(name = "maNguoiMua")
    private NguoiMuaEntity nguoiMua;

    @Column(name = "phanTramDatCoc")
    private BigDecimal phanTramDatCoc;

}
