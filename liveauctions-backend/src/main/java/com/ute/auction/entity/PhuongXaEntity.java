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
@Table(name = "phuong_xa")
public class PhuongXaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maPhuongXa")
    private Long maPhuongXa;

    @Column(name = "tenPhuongXa")
    private String tenPhuongXa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maQuanHuyen")
    private QuanHuyenEntity quanHuyen;

    @OneToMany(mappedBy = "phuongXa")
    private List<NhaKhoEntity> nhaKhos = new ArrayList<>();

    @OneToMany(mappedBy = "phuongXa")
    private List<NguoiDungEntity> nguoiDungs = new ArrayList<>();

}
