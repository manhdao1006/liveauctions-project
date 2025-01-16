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
@Table(name = "danh_muc")
public class DanhMucEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDanhMuc")
    private Long maDanhMuc;

    @Column(name = "tenDanhMuc")
    private String tenDanhMuc;

    @OneToMany(mappedBy = "danhMuc")
    private List<DanhMucConEntity> danhMucCons = new ArrayList<>();

}
