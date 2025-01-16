package com.ute.auction.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nguoi_dung")
public class NguoiDungEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maNguoiDung")
    private Long maNguoiDung;

    @Column(name = "hoVaTen")
    private String hoVaTen;

    @Column(name = "email")
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "Email phải hợp lệ")
    private String email;

    @Column(name = "matKhau")
    private String matKhau;

    @Column(name = "soDienThoai")
    private String soDienThoai;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = " trangThaiHoatDong")
    private String trangThaiHoatDong;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "ngaySinh")
    private LocalDate ngaySinh;

    @Column(name = "gioiTinh")
    private String gioiTinh;

    @Column(name = "trangThaiXoa", nullable = false)
    private String trangThaiXoa = "1";

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "vaitro_nguoidung", joinColumns = @JoinColumn(name = "maNguoiDung", referencedColumnName = "maNguoiDung"), inverseJoinColumns = @JoinColumn(name = "maVaiTro", referencedColumnName = "maVaiTro"))
    private List<VaiTroEntity> vaiTros = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhuongXa")
    private PhuongXaEntity phuongXa;

    @OneToOne(mappedBy = "nguoiDung")
    private NguoiMuaEntity nguoiMua;

    @OneToOne(mappedBy = "nguoiDung")
    private NguoiBanEntity nguoiBan;

    @OneToOne(mappedBy = "nguoiDung")
    private NhanVienEntity nhanVien;

}
