package com.ute.auction.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.SanPhamDauGiaEntity;
import com.ute.auction.entity.impl.MaSanPhamDauGia;

@Repository
public interface SanPhamDauGiaRepository extends JpaRepository<SanPhamDauGiaEntity, MaSanPhamDauGia> {

    Optional<SanPhamDauGiaEntity> findOneByMaSanPhamDauGia_MaPhienDauGiaAndMaSanPhamDauGia_MaSanPham(long maPhienDauGia,
            String maSanPham);

    List<SanPhamDauGiaEntity> findFirst2ByPhienDauGia_TrangThaiHoatDongOrderByPhienDauGia_NgayBatDauAsc(
            String trangThaiHoatDong);

    List<SanPhamDauGiaEntity> findFirst8ByPhienDauGia_TrangThaiHoatDongOrderBySanPham_GiaKhoiDiemDesc(
            String trangThaiHoatDong);

    List<SanPhamDauGiaEntity> findFirst8ByPhienDauGia_TrangThaiHoatDongAndPhienDauGia_LoaiDauGia_TenLoaiDauGiaOrderByPhienDauGia_NgayBatDauAsc(
            String trangThaiHoatDong, String tenLoaiDauGia);

    List<SanPhamDauGiaEntity> findFirst6ByPhienDauGia_TrangThaiHoatDongAndPhienDauGia_LoaiDauGia_TenLoaiDauGiaOrderByPhienDauGia_NgayBatDauAsc(
            String trangThaiHoatDong, String tenLoaiDauGia);

}
