package com.ute.auction.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nha_tham_dinh")
public class NhaThamDinhEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maNhaThamDinh")
    private Long maNhaThamDinh;

    @Column(name = "hoVaTen")
    private String hoVaTen;

    @Column(name = "email")
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "Email phải hợp lệ")
    private String email;

    @Column(name = "gioiTinh")
    private String gioiTinh;

    @Column(name = "soDienThoai")
    private String soDienThoai;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "loai")
    private String loai;

    @Column(name = "trangThaiHoatDong")
    private String trangThaiHoatDong;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "ngaySinh")
    private LocalDate ngaySinh;

    @Column(name = "moTa", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "trangThaiXoa", nullable = false)
    private String trangThaiXoa = "1";

    @OneToMany(mappedBy = "nhaThamDinh")
    private List<SanPhamEntity> sanPhams = new ArrayList<>();

}
