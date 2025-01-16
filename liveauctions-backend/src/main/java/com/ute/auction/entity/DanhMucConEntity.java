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
@Table(name = "danh_muc_con")
public class DanhMucConEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDanhMucCon")
    private Long maDanhMucCon;

    @Column(name = "tenDanhMucCon")
    private String tenDanhMucCon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maDanhMuc")
    private DanhMucEntity danhMuc;

    @OneToMany(mappedBy = "danhMucCon")
    private List<SanPhamDangKyEntity> sanPhamDangKys = new ArrayList<>();

    @OneToMany(mappedBy = "danhMucCon")
    private List<SanPhamEntity> sanPhams = new ArrayList<>();

}
