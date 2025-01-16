package com.ute.auction.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhan_vien")
public class NhanVienEntity {

    @Id
    @Column(name = "maNhanVien")
    private Long maNhanVien;

    @Column(name = "viTri")
    private String viTri;

    @Column(name = "trangThaiXoa", nullable = false)
    private String trangThaiXoa = "1";

    @MapsId
    @OneToOne
    @JoinColumn(name = "maNhanVien", referencedColumnName = "id")
    private NguoiDungEntity nguoiDung;

    @OneToMany(mappedBy = "nhanVien")
    private List<PhienDauGiaEntity> phienDauGias = new ArrayList<>();

}
