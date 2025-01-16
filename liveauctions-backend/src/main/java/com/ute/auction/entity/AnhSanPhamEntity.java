package com.ute.auction.entity;

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
@Table(name = "anh_san_pham")
public class AnhSanPhamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maAnhSanPham")
    private Long maAnhSanPham;

    @Column(name = "tenAnh")
    private String tenAnh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maSanPham")
    private SanPhamEntity sanPham;

}
