package com.ute.auction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.auction.entity.SanPhamDauGiaEntity;
import com.ute.auction.entity.impl.MaSanPhamDauGia;

@Repository
public interface SanPhamDauGiaRepository extends JpaRepository<SanPhamDauGiaEntity, MaSanPhamDauGia> {

    Optional<SanPhamDauGiaEntity> findOneByMaSanPhamDauGia_MaPhienDauGiaAndMaSanPhamDauGia_MaSanPham(long maPhienDauGia,
            String maSanPham);

}
