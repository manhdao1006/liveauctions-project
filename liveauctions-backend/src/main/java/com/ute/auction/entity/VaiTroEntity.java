package com.ute.auction.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vai_tro")
public class VaiTroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maVaiTro")
    private Long maVaiTro;

    @Column(name = "tenVaiTro")
    private String tenVaiTro;

    @ManyToMany(mappedBy = "vaiTros")
    private List<NguoiDungEntity> nguoiDungs = new ArrayList<>();

}
