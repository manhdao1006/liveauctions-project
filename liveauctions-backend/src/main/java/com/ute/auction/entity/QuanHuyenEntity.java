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
@Table(name = "quan_huyen")
public class QuanHuyenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maQuanHuyen")
    private Long maQuanHuyen;

    @Column(name = "tenQuanHuyen")
    private String tenQuanHuyen;

    @OneToMany(mappedBy = "quanHuyen")
    private List<PhuongXaEntity> phuongXas = new ArrayList<>();

}
